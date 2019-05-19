package com.zan.hu.mybatis.sql;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-18 14:57
 * @Description todo
 **/
public class BoundSqlSource implements SqlSource {

    BoundSql boundSql;

    public BoundSqlSource(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    @Override
    public BoundSql getBoundSql(Object o) {
        return boundSql;
    }
}
