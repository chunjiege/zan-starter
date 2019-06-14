package com.zan.hu;

import com.zan.hu.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-14 11:17
 * @Description todo
 **/
@FeignClient("user-service")
public interface FeignService {

    User getByGuid(String guid);
}
