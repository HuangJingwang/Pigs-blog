package com.pigs.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //    跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        跨域配置，允许当前所有接口给 8080(前端)访问
        registry.addMapping("/**")  // 拦截所有的请求
                .allowedOrigins("http://localhost:8080", "https://localhost:8080", "http://49.233.45.84/", "https://www.starrysummer.com", "http://www.starrysummer.com")  // 可跨域的域名，可以为 *
                .allowCredentials(true)
                .allowedMethods("*")   // 允许跨域的方法，可以单独配置
                .allowedHeaders("*");  // 允许跨域的请求头，可以单独配置

    }
}
