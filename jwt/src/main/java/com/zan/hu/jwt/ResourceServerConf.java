package com.zan.hu.jwt;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 13:30
 * @Description todo
 **/
public class ResourceServerConf extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore())
                .resourceId(resourceId());
    }

    public TokenStore tokenStore() {
        return null;
    }

    public String resourceId() {
        return null;
    }
}
