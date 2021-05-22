package com.devilpanda.auth_service.app.api;

import javax.servlet.http.HttpServletRequest;

public interface JwtService {
    String getJwtToken(HttpServletRequest request);

    String generateToken(String login);

    String getUserNameFromToken(String token);

    boolean validateToken(String token);
}
