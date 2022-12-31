package com.pigs.blog.common;

public enum ErrorCode {
    SERVER_ERROR(500, "there is a error in server"),
    PARAMETER_IS_ILLEGAL(10001, "parameter is illegal");
    private Integer code;
    private String msg;
    ErrorCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
