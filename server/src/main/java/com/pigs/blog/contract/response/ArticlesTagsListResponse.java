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
public class ArticlesTagsListResponse {
    @ApiModelProperty("id")
    private Long id;//id

    @ApiModelProperty("标签名称")
    private String tagName;//标签名称

    @ApiModelProperty("标签创建人")
    private String createBy;//标签创建人

    @ApiModelProperty(hidden = true)
    private Date createAt;

    @ApiModelProperty(hidden = true)
    private Date updateAt;
}
