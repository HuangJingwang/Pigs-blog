package com.pigs.blog.contract.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddPictureToWarehouseRequest {
    @NotBlank(message = "name cannot be blank")
    private String name;//图片名称

    @NotBlank(message = "path cannot be blank")
    private String path;//图片路径

    private String description;//图片描述

    private String uploadBy;//上传人

    private String position;//图片所在位置
}
