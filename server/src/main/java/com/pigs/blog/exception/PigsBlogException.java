package com.pigs.blog.exception;

public class PigsBlogException extends RuntimeException{
    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PigsBlogException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public PigsBlogException(String message, Integer code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public PigsBlogException(String message, Throwable cause, Integer code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public PigsBlogException(Throwable cause, Integer code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public PigsBlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
