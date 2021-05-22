package com.devilpanda.auth_service.adapter.user;

import com.devilpanda.auth_service.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @PostMapping("/rest/api/user")
    void createUser(@RequestBody User user);

    @GetMapping("/rest/api/user/login")
    User findUserByLogin(@RequestParam String login);

    @GetMapping("/rest/api/user/email")
    User findUserByEmail(@RequestParam String email);
}
