package com.zan.hu.response.advice.entity;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 16:50
 * @Description todo
 **/
public class SystemExceptionImpl extends BusinessException {

    public SystemExceptionImpl(String code) {
        super(code);
    }

    public SystemExceptionImpl(String code, String message) {
        super(code, message);
    }

    public SystemExceptionImpl(String code, Throwable throwable) {
        super(code, throwable);
    }

    public SystemExceptionImpl(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
