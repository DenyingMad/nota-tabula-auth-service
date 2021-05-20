package com.devilpanda.auth_service.adapter.rest.dto;

import lombok.Data;

@Data
public class LoginFormDto {
    private String email;
    private String password;
}
