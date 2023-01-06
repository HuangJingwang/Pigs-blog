package com.pigs.blog.contract.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ArticlesGroupCreateRequest {
    @ApiModelProperty("id不为空，就是修改接口，否则就是添加接口")
    private Integer id;//id不为空，就是修改接口，否则就是添加接口

    @ApiModelProperty("分组名称")
    @NotBlank(message = "groupName cannot be blank")
    private String groupName;//分组名称


    @ApiModelProperty("父组id，没有父组就是0")
    @NotBlank
    private Integer parentId;//父组id，没有父组就是0

    @ApiModelProperty("创作者")
    @NotBlank(message = "author cannot be blank")
    private String author;//创作者
}
