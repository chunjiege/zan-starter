package com.zan.hu.response.advice.entity;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 16:52
 * @Description todo
 **/
public class BusinessExceptionImpl extends BusinessException {


    public BusinessExceptionImpl(String code) {
        super(code);
    }

    public BusinessExceptionImpl(String code, String message) {
        super(code, message);
    }

    public BusinessExceptionImpl(String code, Throwable throwable) {
        super(code, throwable);
    }

    public BusinessExceptionImpl(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
