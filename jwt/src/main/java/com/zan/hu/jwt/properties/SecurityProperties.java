package com.zan.hu.jwt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-30 17:00
 * @Description todo
 **/
@ConfigurationProperties("security")
@Data
public class SecurityProperties {

    private JwtProperties jwt;

    @Data
    public static class JwtProperties {
        private Resource publicKey;
    }
}
