package com.zan.hu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zan.hu.user.model.User;
import lombok.Data;
import org.springframework.util.StringUtils;

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


    public static CurrentRelatedInfo getInstance(Map<String, Object> currentThreadData, ObjectMapper objectMapper) {
        String guid = currentThreadData.get("guid").toString();
        if (StringUtils.isEmpty(guid))
            return null;
        User user = null;
        try {
            Map<String, Object> temp = (Map<String, Object>) currentThreadData.get("user");
            String json = objectMapper.writeValueAsString(temp);
            user = objectMapper.readValue(json, User.class);
        } catch (Exception e) {

        }

        return new CurrentRelatedInfo(user, guid);
    }
}
