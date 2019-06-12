package com.zan.hu;

import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-03 17:47
 * @Description todo
 **/
public class CommonThreadLocal {

    private static ThreadLocal<Map<String,Object>> currentTreadDataThreadLocal = new ThreadLocal<>();

    public static Map<String,Object> get() {
        return currentTreadDataThreadLocal.get();
    }

    public static void set(Map<String,Object> currentThreadData) {
        currentTreadDataThreadLocal.set(currentThreadData);
    }

    public static void remove() {
        currentTreadDataThreadLocal.remove();
    }
}
