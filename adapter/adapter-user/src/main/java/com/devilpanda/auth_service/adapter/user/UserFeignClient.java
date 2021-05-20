package com.devilpanda.auth_service.adapter.user;

import com.devilpanda.auth_service.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @PostMapping("/rest/api/user")
    void createUser(@RequestBody User user);

    @GetMapping("/user/get/login")
    User findUserByLogin(@RequestBody CredentialDto login);

    @GetMapping("/user/get/login-and-password")
    User findUserByLoginAndPassword(@RequestBody CredentialDto credential);
}
