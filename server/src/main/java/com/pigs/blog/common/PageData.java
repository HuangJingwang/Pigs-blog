package com.pigs.blog.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
public class PageData<T> {
    @ApiModelProperty("数据列表")
    private List<T> resultList; //数据列表
    @ApiModelProperty("总数")
    private Long totalResult; //总数
    @ApiModelProperty("是否有下一页")
    private Boolean hasNext; //是否有下一页
}
