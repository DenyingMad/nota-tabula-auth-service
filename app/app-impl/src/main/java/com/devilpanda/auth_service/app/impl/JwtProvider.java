package com.devilpanda.auth_service.app.impl;

import com.devilpanda.auth_service.app.api.JwtService;
import com.devilpanda.auth_service.domain.UserPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class JwtProvider implements JwtService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
    private static final String JWT_SIGN_SECRET = "STUB_SECRET";
    private static final String AUTHORIZATION = "Authorization";

    @Override
    public String getJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer"))
            return authHeader.replace("Bearer ", "");
        return null;
    }

    @Override
    public String generateToken(Authentication login) {
        UserPrincipal userPrincipal = (UserPrincipal) login.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(15, DAYS)))
                .claim("currentUser", toJsonString(userPrincipal))
                .signWith(SignatureAlgorithm.HS512, JWT_SIGN_SECRET)
                .compact();
    }

    @Override
    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SIGN_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SIGN_SECRET).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.warn("Expired Jwt Token -> Message: {}", e.getMessage());
        } catch (SignatureException e) {
            LOGGER.warn("Invalid Jwt signature -> Message: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.warn("Unsupported Jwt Token -> Message: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.warn("Malformed Jwt Token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Illegal argument for Jwt Token -> Message: {}", e.getMessage());
        }
        return false;
    }

    private String toJsonString(Serializable object) {
        ObjectWriter writer = new ObjectMapper().writer();
        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(String.format("Could not transform object '%s' to JSON: ", object), e);
        }
    }
}
