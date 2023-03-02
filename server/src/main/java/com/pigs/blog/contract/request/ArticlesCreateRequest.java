package com.pigs.blog.contract.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ArticlesCreateRequest {
    @ApiModelProperty("作者")
    @NotBlank(message = "account cannot be blank")
    private String account;//作者

    @ApiModelProperty("标题")
    @NotBlank(message = "title cannot be blank")
    private String title;//标题

    @ApiModelProperty("分类Id")
    private Integer groupId;//分类

    @ApiModelProperty("状态 默认draft-草稿, published-已发布, deleted-被删除")
    @NotBlank(message = "status cannot be blank")
    private String status;//状态 状态 draft-草稿, published-已发布, deleted-被删除

    @ApiModelProperty("多个tag,逗号分割")
    @NotBlank(message = "tags cannot be blank")
    private String tags;//多个tag,逗号分割

    @ApiModelProperty("文章内容")
    @NotBlank(message = "articleText cannot be blank")
    private String articleText;//文章内容

    @ApiModelProperty("封面图片路径")
    private String imgUrl;

    @ApiModelProperty("简介")
    @NotBlank(message = "introduction cannot be blank")
    private String introduction;//文章内容
}
