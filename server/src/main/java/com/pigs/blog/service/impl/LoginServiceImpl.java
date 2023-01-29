package com.pigs.blog.service.impl;

import com.pigs.blog.common.CommonValue;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.entity.LoginUser;
import com.pigs.blog.contract.request.RegistryRequest;
import com.pigs.blog.mapper.UserInfoMapper;
import com.pigs.blog.mapper.UserMapper;
import com.pigs.blog.model.User;
import com.pigs.blog.model.UserExample;
import com.pigs.blog.model.UserInfo;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        //把完整的用户信息存入redis  userid为key   用户信息为value
        redisCache.setCacheObject(CommonValue.REDIS_TOKEN_PREFIX + userid, loginUser);

        return ResultResponse.success(map);

    }

    @Override
    public ResultResponse logout() {
        //从SecurityContextHolder中的userid
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();

        //根据userid找到redis对应值进行删除
        redisCache.deleteObject(CommonValue.REDIS_TOKEN_PREFIX + userid);
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
            return ResultResponse.fail(10003, "account:{" + request.getAccount() + "} is exist when registry");
        }

        User user = new User();
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        BeanUtils.copyProperties(request, user);
        userMapper.insertSelective(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(request.getNickName());
        userInfo.setAccount(request.getAccount());
        userInfoMapper.insertSelective(userInfo);

        return ResultResponse.success(null);
    }


}
