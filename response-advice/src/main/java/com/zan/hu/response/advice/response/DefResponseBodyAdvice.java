package com.zan.hu.response.advice.response;

import com.zan.hu.response.advice.ResponseBody;
import com.zan.hu.response.advice.interceptor.SwaggerInterceptor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.InputStream;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 16:29
 * @Description todo
 **/
@RestControllerAdvice
public class DefResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return SwaggerInterceptor.isOpenApi();
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (skip(body))
            return body;
        if (body == null) {
            return ResponseBody.builder().success(true).build();
        }
        if (!(body instanceof ResponseBody)) {
            return ResponseBody.builder().success(true).data(body).build();
        }
        return body;
    }

    private boolean skip(Object body) {
        if (body instanceof InputStream) {
            return true;
        }
        return false;
    }
}
