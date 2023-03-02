package com.pigs.blog.contract.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ArticlesListResponse {
    @ApiModelProperty("主键id")
    private Long id;//主键id

    @ApiModelProperty("标题")
    private String title;//标题

    @ApiModelProperty("作者")
    private String account;//作者

    @ApiModelProperty("标签,多个按照逗号分割")
    private String tags;//标签,多个按照逗号分割

    @ApiModelProperty("简介")
    @NotBlank(message = "introduction cannot be blank")
    private String introduction;//文章内容

    @ApiModelProperty("封面图片路径")
    private String imgUrl;//封面图片路径

    @ApiModelProperty("分类Id")
    private Long groupId;//分类

    @ApiModelProperty("分类名称")
    private String groupName;

    @ApiModelProperty("状态 draft-草稿, published-已发布, deleted-被删除")
    private String status;//状态 draft-草稿, published-已发布, deleted-被删除

    @ApiModelProperty("创建于(时间)")
    private Date createAt;//创建于(时间)

    @ApiModelProperty("更新于(时间)")
    private Date updateAt;//更新于(时间)

    @ApiModelProperty("文章浏览量")
    private Long pageView;
}
