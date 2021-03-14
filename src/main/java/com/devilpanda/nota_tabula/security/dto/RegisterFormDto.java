package com.devilpanda.nota_tabula.security.dto;

import lombok.Data;

@Data
public class RegisterFormDto {
    private String login;
    private String email;
    private String password;
}
