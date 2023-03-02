package com.pigs.blog.service.impl;

import com.pigs.blog.common.Constants;
import com.pigs.blog.service.GithubAuthService;
import com.pigs.blog.utils.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class GithubAuthServiceImpl implements GithubAuthService {
    private static final Logger logger = LoggerFactory.getLogger(GithubAuthServiceImpl.class);
    @Autowired
    private RedisCache redisCache;
    /**
     * 服务器github的客户端ID
     */
    @Value("${github.oauth.clientid}")
    private String CLIENT_ID;

    /**
     * 应用秘钥
     */
    @Value("${github.oauth.clientsecret}")
    private String CLIENT_SECRETE;

    /**
     * github的回调地址
     */
    @Value("${github.oauth.callback}")
    private String REDIRECT_URL;

    @Override
    public String getGithubAuthPath(String url) {
        logger.info("is login by github");

        return String.format(Constants.GITHUB_CODE_URL, CLIENT_ID, REDIRECT_URL, Constants.STATE);
    }

    @Override
    public void callback(String code, HttpServletResponse resp) {
        String accessToken = getAccessToken(code);
        getOpenId(accessToken, resp);
    }


    public String getAccessToken(String code) {
        String url = String.format(Constants.GITHUB_TOKEN_URL, CLIENT_ID, CLIENT_SECRETE, code);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        String resp = getRestTemplate().getForObject(uri, String.class);
        if (resp.contains(Constants.ACCESS_TOKEN_NAME)) {
            Map<String, String> map = getParam(resp);
            String access_token = map.get(Constants.ACCESS_TOKEN_NAME);
            return access_token;
        } else {
            throw new RuntimeException(resp);
        }
    }

    public void getOpenId(String accessToken, HttpServletResponse response) {
        String url = Constants.GITHUB_USER_URL;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + accessToken);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(null, headers);
        ResponseEntity<String> resp = getRestTemplate().exchange(url, HttpMethod.GET, request, String.class);
        String result = resp.getBody();

        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");

        logger.error("getAccessToken resp = " + result);
        String key = "user-info-" + uuidStr;
        redisCache.setCacheObject(key, result, 30, TimeUnit.MINUTES);
        try {
            response.sendRedirect("https://www.starrysummer.com/#/index?key=" + key);
        } catch (IOException e) {
            logger.error("redirect fail:" + "https://www.starrysummer.com/#/index?key=");
            e.printStackTrace();
        }
    }


    public RestTemplate getRestTemplate() {// 手动添加
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10 * 1000);
        requestFactory.setReadTimeout(10 * 1000);
        List<HttpMessageConverter<?>> messageConverters = new LinkedList<>();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new SourceHttpMessageConverter<Source>());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    private Map<String, String> getParam(String string) {
        Map<String, String> map = new HashMap();
        String[] kvArray = string.split("&");
        for (int i = 0; i < kvArray.length; i++) {
            String[] kv = kvArray[i].split("=");
            if (kv != null) {
                if (kv.length == 1) {
                    map.put(kv[0], null);
                } else if (kv.length == 2) {
                    map.put(kv[0], kv[1]);
                }
            }
        }
        return map;
    }
}

