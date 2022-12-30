package com.pigs.blog.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("返回的结果")
@AllArgsConstructor
public class ResultResponse<T> implements Serializable {
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("状态码")
    private int code;//返回状态码

    @ApiModelProperty("返回的信息")
    private String msg; //返回的信息

    @ApiModelProperty("返回的数据")
    private T data; //返回的数据

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<T>(true, 201, "success", data);
    }

    public static <T> ResultResponse<T> fail(int code, String msg) {
        return new ResultResponse<T>(false, code, msg, null);
    }
}
