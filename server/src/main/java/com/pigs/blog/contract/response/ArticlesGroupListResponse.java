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
public class ArticlesGroupListResponse {
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty("分类名称")
    private String groupName;//分类名称

    @ApiModelProperty("父组id")
    private Long parentId;//父组id

    @ApiModelProperty("创建于")
    private Date createAt;//创建于

    @ApiModelProperty("更新于")
    private Date updateAt;//更新于

    @ApiModelProperty("是否被删除")
    private Short isDelete;//是否被删除

    @ApiModelProperty("创造者")
    private String account;//创造者
}
