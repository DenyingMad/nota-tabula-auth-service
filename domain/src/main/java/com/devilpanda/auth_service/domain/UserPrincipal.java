package com.devilpanda.auth_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class UserPrincipal implements UserDetails {
    private final String login;

    @JsonIgnore
    private final String password;

    private final Collection<GrantedAuthority> authorities = new HashSet<>();

    public UserPrincipal(String login, String password) {
        this.login = login;
        this.password = password;
        authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public static UserPrincipal build(User user) {
        return new UserPrincipal(
                user.getLogin(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
