package com.devilpanda.auth_service.adapter.rest;

import com.devilpanda.auth_service.adapter.rest.dto.LoginFormDto;
import com.devilpanda.auth_service.adapter.rest.dto.RegisterFormDto;
import com.devilpanda.auth_service.app.api.JwtService;
import com.devilpanda.auth_service.app.api.UserService;
import com.devilpanda.auth_service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest/security")
@RequiredArgsConstructor
public class SecurityController {
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody RegisterFormDto registerFormDto) {
        User user = new User(
                registerFormDto.getLogin(),
                registerFormDto.getEmail(),
                registerFormDto.getPassword(),
                registerFormDto.getIsSubscribed()
        );

        userService.saveUser(user);
        return authenticateUser(mapRegisterFormToLoginForm(registerFormDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginFormDto loginFormDto) {
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(loginFormDto.getEmail(), loginFormDto.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtService.generateToken(auth);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    private LoginFormDto mapRegisterFormToLoginForm(RegisterFormDto registerFormDto) {
        LoginFormDto loginFormDto = new LoginFormDto();
        loginFormDto.setEmail(registerFormDto.getEmail());
        loginFormDto.setPassword(registerFormDto.getPassword());
        return loginFormDto;
    }
}
