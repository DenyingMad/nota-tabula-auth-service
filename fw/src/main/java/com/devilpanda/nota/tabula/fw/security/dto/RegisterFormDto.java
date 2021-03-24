package com.devilpanda.nota.tabula.fw.security.dto;

import lombok.Data;

@Data
public class RegisterFormDto {
    private String login;
    private String email;
    private String password;
}
