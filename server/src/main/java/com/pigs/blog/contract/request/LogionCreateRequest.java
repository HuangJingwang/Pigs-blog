package com.pigs.blog.contract.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LogionCreateRequest {
    @ApiModelProperty("箴言")
    @NotBlank(message = "logion cannot be blank")
    private String logion;//箴言
    @NotBlank(message = "author cannot be blank")
    @ApiModelProperty("作者")
    private String author;//作者
}
