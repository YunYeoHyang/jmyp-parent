package com.czxy.jmyp.client;

import com.czxy.jmyp.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("web-service")
// @Component
public interface UserClient {
    @GetMapping("query")
    ResponseEntity<User> queryUser(@RequestParam("mobile") String mobile,
                                   @RequestParam("password") String password);
}
