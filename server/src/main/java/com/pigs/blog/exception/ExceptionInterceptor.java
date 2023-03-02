package com.pigs.blog.exception;

import com.pigs.blog.common.ResultResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(PigsBlogException.class)
    @ResponseBody
    public ResultResponse handlerPigsBlogException(PigsBlogException e){
        System.out.println(e.getMessage());
        return ResultResponse.fail(e.getCode(),e.getMessage());
    }

    //进行异常处理，对Exception进行处理，即对所有错误进行处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultResponse handlerException(Exception e) {
        e.printStackTrace();
        return ResultResponse.fail(ErrorCodeEnum.SERVER_ERROR.getCode(), ErrorCodeEnum.SERVER_ERROR.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultResponse doValidationException(MethodArgumentNotValidException e){
        return ResultResponse.fail(ErrorCodeEnum.PARAMETER_IS_ILLEGAL.getCode(),e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResultResponse accessException(AccessDeniedException e){
        return ResultResponse.fail(ErrorCodeEnum.ACCESSS_DENIED.getCode(),ErrorCodeEnum.ACCESSS_DENIED.getMsg());
    }
}
