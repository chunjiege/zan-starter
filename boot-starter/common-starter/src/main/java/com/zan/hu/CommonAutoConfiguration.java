package com.zan.hu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zan.hu.common.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-03 17:44
 * @Description todo
 **/
@ConditionalOnProperty(prefix = "current.thread.data", value = "enable", havingValue = "true")
@Configuration
@Slf4j
public class CommonAutoConfiguration {


    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        registration.setName("commonFilter");
        log.info("公用filter注册成功！！！");
        return registration;
    }

    public class CommonFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String details = httpServletRequest.getHeader("details");
            if (!StringUtils.isEmpty(details)) {
                String decode = URLDecoder.decode(details, "UTF-8");
                Map<String, Object> currentThreadData = ObjectMapperUtils.newInstance().readValue(decode, Map.class);
                CurrentRelatedInfo instance = CurrentRelatedInfo.getInstance(currentThreadData);
                CommonThreadLocal.set(instance);
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
            CommonThreadLocal.remove();
        }
    }


    /**
     * 解决java8日期序列化问题
     * http://blog.didispace.com/Spring-Boot-And-Feign-Use-localdate/
     *
     * @return
     */
    @Bean
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
