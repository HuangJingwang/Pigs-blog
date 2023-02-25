package com.pigs.blog.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.*;
import io.swagger.annotations.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HomepageUserInfoResponse {
    @ApiModelProperty(hidden = true)
    private String account;
    //用户昵称
    @ApiModelProperty("用户昵称")
    private String nickname;
    //用户头像
    @ApiModelProperty("用户头像")
    private String imgUrl;
    //用户文章数
    @ApiModelProperty("用户文章数")
    private Integer articlesCount;
    //用户githuburl
    @ApiModelProperty("用户githuburl")
    private String githubUrl;
    //用户文章访问量
    @ApiModelProperty("用户文章访问量")
    private Long pageView;
}
