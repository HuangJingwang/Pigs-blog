package com.pigs.blog.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface GithubAuthService {
    /**
     * 获取github的授权地址
     *
     * @return
     * @param url
     */
    String getGithubAuthPath(String url);

    /**
     * 在github页面登录成功后，回调的处理方法
     *
     * @param code
     * @return
     */
    void callback(String code, HttpServletResponse resp);
}

