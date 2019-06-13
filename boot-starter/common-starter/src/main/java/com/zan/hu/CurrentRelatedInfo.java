package com.zan.hu;

import com.zan.hu.user.model.User;
import lombok.Data;

import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-13 19:22
 * @Description 当前线程的相关信息
 **/
@Data
public class CurrentRelatedInfo {

    private User user;

    private String guid;

    public CurrentRelatedInfo() {
    }

    public CurrentRelatedInfo(User user, String guid) {
        this.user = user;
        this.guid = guid;
    }


    public static CurrentRelatedInfo getInstance(Map<String, Object> currentThreadData) {
        User user = (User) currentThreadData.get("user");
        String gudi = currentThreadData.get("guid").toString();
        return new CurrentRelatedInfo(user, gudi);
    }
}
