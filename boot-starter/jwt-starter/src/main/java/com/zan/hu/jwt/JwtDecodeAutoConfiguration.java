package com.zan.hu.jwt;

import com.zan.hu.jwt.config.FeignConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-09 10:17
 * @Description todo
 **/
@Configuration
@ConditionalOnClass(FeignConfiguration.class)
public class JwtDecodeAutoConfiguration {

    @Bean
    public FeignConfiguration feignConfiguration() {
        return new FeignConfiguration();
    }

    @Bean
    public JwtConf jwtConf() {
        return new JwtConf();
    }

}
