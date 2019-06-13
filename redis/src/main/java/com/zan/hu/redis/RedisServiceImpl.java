package com.zan.hu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-04 14:50
 * @Description todo
 **/
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, long time, Object value) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String... key) {
        if (key != null && key.length > 0) {
            redisTemplate.delete(CollectionUtils.arrayToList(key));
        }
    }

    @Override
    public void add(String key, Object... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Set<Object> members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public void add(String key, long time, Object... values) {
        add(key, values);
        expire(key, time);
    }

    @Override
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return Boolean.TRUE;
        } catch (Exception e) {

        }
        return Boolean.FALSE;
    }
}
