package com.devilpanda.auth_service.adapter.user;

import com.devilpanda.auth_service.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @PostMapping("/rest/api/user")
    void createUser(@RequestBody User user);

    @PutMapping("/rest/api/user/change-password")
    void changePassword(@RequestBody String password);

    @GetMapping("/rest/api/user/login")
    User findUserByLogin(@RequestParam String login);

    @GetMapping("/rest/api/user/email")
    User findUserByEmail(@RequestParam String email);
}
