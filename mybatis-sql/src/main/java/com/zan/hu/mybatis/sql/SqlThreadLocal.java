package com.zan.hu.mybatis.sql;

import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-18 15:23
 * @Description todo
 **/
@Component
public class SqlThreadLocal {

    private static final ThreadLocal<String> sqlThreadLocal = ThreadLocal.withInitial(() -> "");

    public ThreadLocal getThreadLocal() {
        return sqlThreadLocal;
    }
}
