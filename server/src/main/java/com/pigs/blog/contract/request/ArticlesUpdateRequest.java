package com.pigs.blog.contract.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ArticlesUpdateRequest {
    @ApiModelProperty("作者")
    private String account;//作者

    @ApiModelProperty("标题")
    private String title;//标题

    @ApiModelProperty("分类")
    private Integer groupId;//分类

    @ApiModelProperty("状态 状态 draft-草稿, published-已发布, deleted-被删除")
    private String status;//状态 状态 draft-草稿, published-已发布, deleted-被删除

    @ApiModelProperty("多个tag,逗号分割")
    private String tags;//多个tag,逗号分割

    @ApiModelProperty("文章内容")
    private String articleText;//文章内容

    @ApiModelProperty("封面图片")//@NotBlank(message = "imgUrl cannot be blank")
    private String imgUrl;

    @ApiModelProperty("文章简介")
    private String introduction;//文章简介

    @ApiModelProperty("文章图片路径")
    private List<String> articlePictureUrl;//文章图片路径
}
