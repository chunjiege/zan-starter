package com.zan.hu.mybatis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-18 16:03
 * @Description todo
 **/
@Configuration
@ConditionalOnProperty(prefix = "mybatis.sql.interceptor", value = "enable", havingValue = "true")
public class MybatisAutoConfiguration {
}
