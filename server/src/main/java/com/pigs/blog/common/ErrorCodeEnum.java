package com.pigs.blog.common;

public enum ErrorCodeEnum {
    SERVER_ERROR(500, "there is a error in server"),
    PARAMETER_IS_ILLEGAL(10001, "parameter is illegal"),
    PARENT_ID_IS_NOT_EXIST(10002, "parent id is not exist"),
    PARENT_ID_IS_CONFLICT_TO_CURRENT_ID(10003, "parent id is conflict to current id");
    private Integer code;
    private String msg;
    ErrorCodeEnum(Integer code, String msg){
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
