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
public class RegistryRequest {
    @ApiModelProperty("账户")
    @NotBlank(message = "account cannot be blank when registry")
    private String account;//账户

    @ApiModelProperty("密码")
    @NotBlank(message = "password cannot be blank when registry")
    private String password;//密码

    @ApiModelProperty("昵称")
    @NotBlank(message = "nickName cannot be blank when registry")
    private String nickName;//昵称
}
