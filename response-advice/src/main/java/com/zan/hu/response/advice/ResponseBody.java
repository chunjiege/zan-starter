package com.zan.hu.response.advice;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 16:33
 * @Description todo
 **/
@Data
@Builder
public class ResponseBody<T> implements Serializable {
    private Boolean success;
    private String code;
    private String message;
    private T data;
}
