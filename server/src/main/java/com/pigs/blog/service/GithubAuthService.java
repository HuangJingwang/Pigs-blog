package com.pigs.blog.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface GithubAuthService {
    /**
     * 获取github的授权地址
     *
     * @return
     */
    String getGithubAuthPath();

    /**
     * 在github页面登录成功后，回调的处理方法
     *
     * @param code
     * @return
     */
    String callback(String code);
}

