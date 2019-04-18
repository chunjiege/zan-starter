package com.zan.hu.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 13:27
 * @Description todo
 **/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class JwtConf {

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    private final SecurityProperties securityProperties;


    public JwtConf(final SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        // 用作JWT转换器
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(new CustomAccessTokenConverter());
        //设置公钥
        converter.setVerifierKey(getPublicKeyAsString());
        return converter;
    }

    private String getPublicKeyAsString() {
        try {
            return new String(FileCopyUtils.copyToByteArray(securityProperties.getJwt().getPublicKey().getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
