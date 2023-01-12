package com.pigs.blog.service;

import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.model.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    ResultResponse login(User user);

    ResultResponse logout();
}
