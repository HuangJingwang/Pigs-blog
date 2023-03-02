package com.pigs.blog.model.vo;

import lombok.Data;

//首页用户信息展示，查用户信息。包含用户头像、用户昵称、用户文章数、用户文章访问量、用户githuburl
@Data
public class HomepageUserInfo {
    //用户昵称
    private String nickname;
    //用户头像
    private String imgUrl;

    //用户文章数
    private Integer articlesCount;
    //用户githuburl
    private String githubUrl;

}
