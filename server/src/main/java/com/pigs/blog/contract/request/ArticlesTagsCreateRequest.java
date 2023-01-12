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
public class ArticlesTagsCreateRequest {
    @ApiModelProperty("标签名称")
    @NotBlank(message = "tagName cannot be blank")
    private String tagName;//标签名称

    @ApiModelProperty("创建人")
    @NotBlank(message = "createBy cannot be blank")
    private String createBy;//创建人
}
