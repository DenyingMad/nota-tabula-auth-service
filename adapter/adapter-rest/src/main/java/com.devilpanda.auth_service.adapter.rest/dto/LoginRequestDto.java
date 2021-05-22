package com.devilpanda.auth_service.adapter.rest.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
