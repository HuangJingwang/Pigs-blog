package com.pigs.blog.contract.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ArticlesListPageRequest {
    @ApiModelProperty("作者")
    private String author;//作者
    @ApiModelProperty("状态 状态 draft-草稿, published-已发布, deleted-被删除")
    private String status;//状态 状态 draft-草稿, published-已发布, deleted-被删除
    @ApiModelProperty(hidden = true)
    private Integer pageNo;
    @ApiModelProperty(hidden = true)
    private Integer pageSize;

    private Boolean orderByPV;
}
