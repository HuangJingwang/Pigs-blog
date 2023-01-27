package com.pigs.blog.common;

import java.nio.charset.Charset;

public class CommonValue {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    //jwt有效期为
    public static final Long JWT_TTL = 24 * 60 * 60 *1000L;// 24 * 60 * 60 *1000  24小时
    //jwt设置秘钥明文
    public static final String JWT_KEY = "hjw";

    //redis存的登录的key的前缀
    public static final String REDIS_TOKEN_PREFIX = "login:";
}
