package com.zan.hu.response.advice.entity;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 16:47
 * @Description todo
 **/
public enum SystemException implements ExceptionAttribute {

    SYSTEM_EXCEPTION("S_1000", "系统异常"),
    AUTHORITY_EXCEPTION("S_0000", "没有访问权限");

    private String code;
    private String message;

    SystemException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
