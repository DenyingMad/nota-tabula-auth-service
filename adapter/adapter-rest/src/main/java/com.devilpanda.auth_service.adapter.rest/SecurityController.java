package com.devilpanda.auth_service.adapter.rest;

import com.devilpanda.auth_service.adapter.rest.dto.LoginFormDto;
import com.devilpanda.auth_service.adapter.rest.dto.RegisterFormDto;
import com.devilpanda.auth_service.app.api.JwtService;
import com.devilpanda.auth_service.app.api.UserService;
import com.devilpanda.auth_service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest/security")
@RequiredArgsConstructor
public class SecurityController {
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterFormDto registerFormDto) {
        User user = new User(
                registerFormDto.getLogin(),
                registerFormDto.getEmail(),
                registerFormDto.getPassword(),
                registerFormDto.getIsSubscribed()
        );

        userService.saveUser(user);
        return ResponseEntity.ok("Successful registration");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginFormDto loginFormDto) {
        User user = userService.authorizeUser(loginFormDto.getEmail(), loginFormDto.getPassword());

        String jwtToken = jwtService.generateToken(user.getLogin());

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }
}
