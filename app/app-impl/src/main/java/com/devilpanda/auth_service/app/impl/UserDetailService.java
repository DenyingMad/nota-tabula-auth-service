package com.devilpanda.auth_service.app.impl;

import com.devilpanda.auth_service.app.api.UserService;
import com.devilpanda.auth_service.domain.User;
import com.devilpanda.auth_service.domain.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(userName);
        return UserPrincipal.build(user);
    }
}
