package com.zan.hu.jwt;

import com.zan.hu.jwt.properties.WhiteListProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 13:30
 * @Description todo
 **/
@EnableConfigurationProperties(WhiteListProperties.class)
public abstract class ResourceServerConf extends ResourceServerConfigurerAdapter {

    @Autowired
    private WhiteListProperties whiteListProperties;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore)
                .resourceId(resourceId());
    }

    public abstract String resourceId();

    @Override
    public void configure(HttpSecurity http) throws Exception {
        List<String> requestMapping = whiteListProperties.getRequestMapping();
        if (CollectionUtils.isEmpty(requestMapping)) {
            super.configure(http);
            return;
        }
        String[] liString = new String[requestMapping.size()];
        requestMapping.toArray(liString);
        http.authorizeRequests()
                .regexMatchers(liString).permitAll();
    }
}
