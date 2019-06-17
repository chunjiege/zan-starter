package com.zan.hu;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-03 17:47
 * @Description todo
 **/
public class CommonThreadLocal {

    private static ThreadLocal<CurrentRelatedInfo> currentTreadDataThreadLocal = new ThreadLocal<>();

    public static CurrentRelatedInfo get() {
        return currentTreadDataThreadLocal.get();
    }

    public static void set(CurrentRelatedInfo currentRelatedInfo) {
        currentTreadDataThreadLocal.set(currentRelatedInfo);
    }

    public static void remove() {
        currentTreadDataThreadLocal.remove();
    }
}
