package com.zan.hu.redis.redis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-04 14:14
 * @Description todo
 **/
@Data
public class AccessTokenDto implements Serializable {
    private static final long serialVersionUID = -4196434944157405258L;

    private String accessToken;

    private Long expiresIn;

    private String guid;
}
