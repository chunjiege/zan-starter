package com.zan.hu.redis;

import java.util.Set;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-11 13:43
 * @Description todo
 **/
public interface RedisService {

    /***
     * key
     * @param key
     * @return
     */

    Object get(String key);

    void set(String key, Object value);

    void set(String key, long time, Object value);

    void remove(String... key);

    //** set 集合

    /**
     * 向变量中批量添加值。
     *
     * @param key
     * @param values
     */
    void add(String key, Object... values);

    /**
     * 获取变量中的值。
     *
     * @param key
     * @return
     */
    Set<Object> members(String key);

    void add(String key, long time, Object... values);

    void setRemove(String key, String value);

    //指定缓存失效的时间
    boolean expire(String key, long time);
}
