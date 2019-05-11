package com.zan.hu.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 13:27
 * @Description todo
 **/
public class JwtConf {

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

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
            ClassPathResource classPathResource = new ClassPathResource("public.cert");
            InputStream inputStream = classPathResource.getInputStream();
            return new String(FileCopyUtils.copyToByteArray(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
