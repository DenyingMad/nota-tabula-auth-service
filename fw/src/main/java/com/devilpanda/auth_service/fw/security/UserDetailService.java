package com.devilpanda.auth_service.fw.security;

import com.devilpanda.auth_service.app.api.UserService;
import com.devilpanda.auth_service.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(userName).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s not found...", userName)));
        return UserPrincipal.build(user);
    }
}
