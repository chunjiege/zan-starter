package com.zan.hu.jwt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-18 20:42
 * @Description todo
 **/
@ConfigurationProperties(prefix = "white.list")
@Data
public class WhiteListProperties {

    List<String> requestMapping = new ArrayList<>();
}
