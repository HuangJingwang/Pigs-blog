package com.pigs.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pigs.blog.common.Constants;
import com.pigs.blog.common.RoleEnum;
import com.pigs.blog.exception.ErrorCodeEnum;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.entity.LoginUser;
import com.pigs.blog.contract.request.RegistryRequest;
import com.pigs.blog.contract.response.LoginUserDataResponse;
import com.pigs.blog.mapper.UserInfoMapper;
import com.pigs.blog.mapper.UserMapper;
import com.pigs.blog.mapper.ext.UserMapperExt;
import com.pigs.blog.model.User;
import com.pigs.blog.model.UserExample;
import com.pigs.blog.model.UserInfo;
import com.pigs.blog.model.UserInfoExample;
import com.pigs.blog.service.LoginService;
import com.pigs.blog.utils.JwtUtil;
import com.pigs.blog.utils.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserMapperExt userMapperExt;

    @Override
    public ResultResponse login(User user) {

        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());

        //AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回

        //如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        Map<String, String> map = doLogin(loginUser);
        return ResultResponse.success(map);

    }

    @Override
    public ResultResponse logout() {
        //从SecurityContextHolder中的userId
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();

        //根据userId找到redis对应值进行删除
        redisCache.deleteObject(Constants.REDIS_TOKEN_PREFIX + userid);
        return ResultResponse.success(null);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ResultResponse registry(RegistryRequest request) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(request.getAccount());
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            logger.error("account:{" + request.getAccount() + "} is exist when registry");
            return ResultResponse.fail(10005, "account:{" + request.getAccount() + "} is exist when registry");
        }

        User user = new User();
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        BeanUtils.copyProperties(request, user);
        user.setRole(RoleEnum.VISITOR.getRole());
        userMapper.insertSelective(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(request.getNickName());
        userInfo.setAccount(request.getAccount());
        userInfo.setGithubUrl(request.getGithubUrl());
        userInfo.setImgUrl(request.getImgUrl());
        userInfoMapper.insertSelective(userInfo);

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        Map<String, String> map = doLogin(loginUser);
        return ResultResponse.success(map);
    }

    @Override
    public ResultResponse getGitHubUserInfo(String key) {
        Object value = redisCache.getCacheObject(key);

        //1.如果redis数据不存在，则github登录数据过期
        if (value == null) {
            return ResultResponse.fail(ErrorCodeEnum.GITHUB_LOGIN_DATA_IS_EXPIRE.getCode(), ErrorCodeEnum.GITHUB_LOGIN_DATA_IS_EXPIRE.getMsg());
        }

        // 解析redis中用户数据，获取GitHubID
        String userInfo = value.toString();
        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        Object id = jsonObject.get("id");
        long githubId = Long.parseLong(id.toString());

        // 根据GithubID查看用户是否存在
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andGithubIdEqualTo(githubId);
        List<User> users = userMapper.selectByExample(userExample);

        //2. 如果用户不存在，就返回GitHub用户信息，让前端跳转到注册页面
        if (CollectionUtils.isEmpty(users)) {
            return ResultResponse.success(userInfo);

        } else {

            //3. 如果用户存在，就直接登录，并且返回token
            User user = CollectionUtils.firstElement(users);

            LoginUser loginUser = new LoginUser();
            loginUser.setUser(user);
            Map<String, String> map = doLogin(loginUser);
            return ResultResponse.success(map);

        }
    }


    private Map<String, String> doLogin(LoginUser loginUser){
        User user = loginUser.getUser();
        String userId = user.getId().toString();

        //将用户数据放入redis
        redisCache.setCacheObject(Constants.REDIS_TOKEN_PREFIX + userId, loginUser);

        //返回token和用户数据
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();

        //返回对应的userInfo数据
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(user.getAccount());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);

        LoginUserDataResponse userData = new LoginUserDataResponse();
        BeanUtils.copyProperties(user,userData);

        userData.setRole(Arrays.asList(user.getRole().split(",")));

        if(!CollectionUtils.isEmpty(userInfos)){
            BeanUtils.copyProperties(CollectionUtils.firstElement(userInfos),userData);
            map.put("user_data", JSONObject.toJSONString(userData));
        }
        map.put("token", jwt);
        return map;
    }
}
