package com.pigs.blog.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PicturesListResponse {
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty("图片名称")
    private String name;//图片名称

    @ApiModelProperty("图片路径")
    private String path;//图片路径

    @ApiModelProperty("描述")
    private String description;//描述

    @ApiModelProperty("上传人")
    private String uploadBy;//上传人

    @ApiModelProperty("创建于")
    private Date createAt;//创建于

    @ApiModelProperty("更新于")
    private Date updateAt;//更新于

    @ApiModelProperty("所用的位置")
    private String position;//所用的位置
}
