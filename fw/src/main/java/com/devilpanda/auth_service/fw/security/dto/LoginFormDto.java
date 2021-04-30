package com.devilpanda.auth_service.fw.security.dto;

import lombok.Data;

@Data
public class LoginFormDto {
    private String email;
    private String password;
}
