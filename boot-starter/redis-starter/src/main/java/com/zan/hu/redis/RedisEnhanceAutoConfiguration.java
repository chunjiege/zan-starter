package com.zan.hu.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 13:47
 * @Description todo
 **/
@Configuration
public class RedisEnhanceAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "redis.basic.server", value = "enable", havingValue = "true")
    public RedisService redisService() {
        return new RedisServiceImpl();
    }
}
