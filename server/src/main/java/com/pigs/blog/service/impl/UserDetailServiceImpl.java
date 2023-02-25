package com.pigs.blog.service.impl;

import com.pigs.blog.contract.entity.LoginUser;
import com.pigs.blog.exception.ErrorCodeEnum;
import com.pigs.blog.exception.PigsBlogException;
import com.pigs.blog.mapper.UserMapper;
import com.pigs.blog.model.User;
import com.pigs.blog.model.UserExample;
import io.jsonwebtoken.lang.Collections;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        //LOG.error("登陆用户输入的用户名：{}",s);

        //根据用户名查找用户信息
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        List<User> users = userMapper.selectByExample(example);

        if (Collections.isEmpty(users)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        User user = CollectionUtils.firstElement(users);

        //查询对应权限
        if (Strings.isBlank(user.getRole())) {
            throw new PigsBlogException(403, "当前用户没有任何权限,请确认是否有游客权限");
        }
        List<String> roles = Arrays.asList(user.getRole().split(","));


        //String cryptPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
        String cryptPwd = passwordEncoder.encode("123");
        System.out.println(cryptPwd + "--------------------");

        // LOG.error("加密后的密码为: {}",cryptPwd);

        return new LoginUser(user, roles); //账号 密码 权限
    }
}
