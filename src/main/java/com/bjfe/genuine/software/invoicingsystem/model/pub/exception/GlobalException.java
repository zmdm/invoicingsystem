package com.bjfe.genuine.software.invoicingsystem.model.pub.exception;

/**
 * Created by Administrator on 2017-11-9.
 */

/**
 * 自定义异常类
 */
public class GlobalException extends RuntimeException{
    private String message;

    public GlobalException(){
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GlobalException(String message){
        super(message);
        this.message=message;
    }
}
