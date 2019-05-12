package com.zan.hu.response.advice.entity;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 16:43
 * @Description todo
 **/
public class BusinessException extends RuntimeException {

    private String code;

    public String getCode() {
        return code;
    }

    public BusinessException(String code) {
        this(code, null, null);
    }

    public BusinessException(String code, String message) {
        this(code, message, null);
    }

    public BusinessException(String code, Throwable throwable) {
        this(code, null, throwable);
    }

    public BusinessException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public static BusinessExceptionBuild biz(String code) {
        return new BusinessExceptionBuild("bus", code);
    }

    public static BusinessExceptionBuild sys(String code) {
        return new BusinessExceptionBuild("sys", code);
    }

    public static BusinessExceptionBuild biz(ExceptionAttribute exceptionAttribute) {
        return new BusinessExceptionBuild("bus", exceptionAttribute);
    }

    public static BusinessExceptionBuild sys(ExceptionAttribute exceptionAttribute) {
        return new BusinessExceptionBuild("sys", exceptionAttribute);
    }


    public static class BusinessExceptionBuild {

        private String type;

        private String code;

        private String message;

        private Throwable cause;

        public BusinessExceptionBuild(String type, String code) {
            this.type = type;
            this.code = code;
        }

        public BusinessExceptionBuild(String type, ExceptionAttribute exceptionAttribute) {
            this.type = type;
            this.code = exceptionAttribute.getCode();
            this.message = exceptionAttribute.getMessage();
        }

        public BusinessExceptionBuild message(String message, Object... args) {
            this.message = String.format(message, args);
            return this;
        }

        public BusinessExceptionBuild cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public void make() {
            throw build();
        }


        public BusinessException build() {

            if ("sys".equalsIgnoreCase(type)) {
                return new SystemExceptionImpl(code, message, cause);
            }
            if ("bus".equalsIgnoreCase(type)) {
                return new BusinessExceptionImpl(code, message, cause);
            }
            throw new IllegalArgumentException(String.format("Exception type %s not supported yet!", type));
        }

    }
}
