package com.pigs.blog.common;

import java.nio.charset.Charset;

public class Constants {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    /**
     * redis相关
     */
    //jwt有效期为
    public static final Long JWT_TTL = 24 * 60 * 60 *1000L;// 24 * 60 * 60 *1000  24小时
    //jwt设置秘钥明文
    public static final String JWT_KEY = "hjw";

    //redis存的登录的key的前缀
    public static final String REDIS_TOKEN_PREFIX = "login:";

    /**
     * 七牛云
     */
    //设置好账号的ACCESS_KEY和SECRET_KEY
    public static final String ACCESS_KEY = "7uHE8tBEEQ90sguVWuERGMsLlcwdSYSaw0VZvBUs"; //这两个登录七牛 账号里面可以找到
    public static final String SECRET_KEY = "K5cFU5DkvvEmOT_neflVOyxdihcxTf_An0TdslAg";

    //要上传的空间
    public static final String bucketName = "pigs-blog"; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置私有）
    // 测试域名，只有30天有效期
    public static final String QINIU_IMAGE_DOMAIN = "https://moon.starrysummer.com/";

    /**
     * GitHub登录相关
     */
    //github的授权地址
    public final static String GITHUB_CODE_URL = "https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&state=%s";

    //获取github用户ACCESS_TOKEN地址
    public final static String GITHUB_TOKEN_URL = "https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s";

    //获取github用户信息
    public final static String GITHUB_USER_URL = "https://api.github.com/user?";

    public final static String ACCESS_TOKEN_NAME = "access_token";

    //该字段的值会回传回来
    public final static String STATE = "true";

    //网站信息
    public final static String WEBSITE_IMG_URL = "https://moon.starrysummer.com/3686fd078f7649528d5b5ba31de2a9d7.jpg";
    public final static String WEBSITE_GITHUB_URL = "https://github.com/HuangJingwang/Pigs-blog";
    public final static String WEBSITE_NICKNAME = "StarrySummer";
}
