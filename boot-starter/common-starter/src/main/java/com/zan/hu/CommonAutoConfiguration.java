package com.zan.hu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.form.spring.SpringFormEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
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
@Configuration
@Slf4j
public class CommonAutoConfiguration {

    @Autowired
    private ObjectMapper objectMapper;


    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        registration.setName("commonFilter");
        log.info("CommonFilter注册成功！！！");
        return registration;
    }

    public class CommonFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String details = httpServletRequest.getHeader("details");
            String from = httpServletRequest.getHeader("from");
            if (StringUtils.isEmpty(from) && !StringUtils.isEmpty(details)) {
                String decode = URLDecoder.decode(details, "UTF-8");
                Map<String, Object> currentThreadData = objectMapper.readValue(decode, Map.class);
                CurrentRelatedInfo instance = CurrentRelatedInfo.getInstance(currentThreadData, objectMapper);
                CommonThreadLocal.set(instance);
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
            log.info("当前线程：" + Thread.currentThread().getName() + "正在清除ThreadLocal中的数据");
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

    @Bean
    @ConditionalOnClass(SpringFormEncoder.class)
    @ConditionalOnProperty(prefix = "zan.feign.multipart", name = "enabled", matchIfMissing = true)
    public FeignMultipartSupportConfig feignMultipartSupportConfig() {
        log.info("FeignMultipartSupportConfig register successfully");
        return new FeignMultipartSupportConfig();
    }
}
