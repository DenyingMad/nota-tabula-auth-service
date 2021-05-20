package com.devilpanda.auth_service.adapter.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDto {
    private String login;
    private String email;
    private String password;
}
