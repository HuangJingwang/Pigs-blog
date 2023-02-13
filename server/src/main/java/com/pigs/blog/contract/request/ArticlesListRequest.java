package com.pigs.blog.contract.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ArticlesListRequest {
    @ApiModelProperty("分类id")
    private Integer groupId;//分类id
    @ApiModelProperty("标签")
    private String tags;//标签
    @ApiModelProperty("作者")
    private String account;//作者
    @ApiModelProperty("状态")
    private String status;//状态
}
