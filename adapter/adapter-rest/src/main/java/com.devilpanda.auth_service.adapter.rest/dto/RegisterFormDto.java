package com.devilpanda.auth_service.adapter.rest.dto;

import lombok.Data;

@Data
public class RegisterFormDto {
    private String login;
    private String email;
    private String password;
    private Boolean isSubscribed;
}
