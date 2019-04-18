package com.zan.hu.jwt;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-09 11:34
 * @Description todo
 **/

public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication authentication
                = super.extractAuthentication(claims);
        authentication.setDetails(claims);
        return authentication;
    }
}
