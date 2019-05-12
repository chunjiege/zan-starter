package com.zan.hu.response.advice.exception;

import com.zan.hu.response.advice.ResponseBody;
import com.zan.hu.response.advice.entity.BusinessException;
import com.zan.hu.response.advice.entity.SystemException;
import com.zan.hu.response.advice.interceptor.SwaggerInterceptor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 16:37
 * @Description todo
 **/
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseBody allExceptionHandler(Exception exception) throws Exception {
        if (!SwaggerInterceptor.isOpenApi()) {
            throw exception;
        }
        BusinessException definitionBusinessException = findDefinitionBusinessException(exception);
        if (definitionBusinessException == null) {
            if (exception instanceof AccessDeniedException) {
                definitionBusinessException =
                        BusinessException.sys(SystemException.AUTHORITY_EXCEPTION).cause(exception).build();
            } else {
                definitionBusinessException =
                        BusinessException.sys(SystemException.SYSTEM_EXCEPTION).cause(exception).build();
            }
        }
        log.error(definitionBusinessException.toString(), definitionBusinessException);
        return ResponseBody.builder().success(false).
                code(definitionBusinessException.getCode()).message(definitionBusinessException.getMessage()).build();
    }

    public BusinessException findDefinitionBusinessException(Throwable throwable) {
        while (throwable != null) {
            if (throwable instanceof BusinessException) {
                return (BusinessException) throwable;
            }
            throwable = throwable.getCause();
        }
        return null;
    }
}
