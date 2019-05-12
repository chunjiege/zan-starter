package com.zan.hu.response.advice;

import com.zan.hu.response.advice.exception.ExceptionAdvice;
import com.zan.hu.response.advice.interceptor.SwaggerInterceptor;
import com.zan.hu.response.advice.response.DefResponseBodyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 17:02
 * @Description todo
 **/
@Configuration
public class ResponseAdviceConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(swaggerInterceptor());
    }

    private SwaggerInterceptor swaggerInterceptor() {
        return new SwaggerInterceptor();
    }

//    @Bean("defAdvice")
//    public DefResponseBodyAdvice defResponseBodyAdvice() {
//        return new DefResponseBodyAdvice();
//    }
//
//    @Bean("")
//    public ExceptionAdvice exceptionAdvice() {
//        return new ExceptionAdvice();
//    }
}
