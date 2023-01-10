package com.pigs.blog.utils;

import com.pigs.blog.common.ErrorCode;
import com.pigs.blog.common.ResultResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionInterceptor {

    //进行异常处理，对Exception进行处理，即对所有错误进行处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultResponse doException(Exception e) {
        e.printStackTrace();
        return ResultResponse.fail(ErrorCode.SERVER_ERROR.getCode(), ErrorCode.SERVER_ERROR.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultResponse doValidationException(MethodArgumentNotValidException e){
        return ResultResponse.fail(ErrorCode.PARAMETER_IS_ILLEGAL.getCode(),e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
