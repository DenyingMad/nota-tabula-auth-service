package com.devilpanda.auth_service.adapter.rest;

import com.devilpanda.auth_service.adapter.rest.dto.LoginRequestDto;
import com.devilpanda.auth_service.adapter.rest.dto.RegisterRequestDto;
import com.devilpanda.auth_service.app.api.JwtService;
import com.devilpanda.auth_service.app.api.UserService;
import com.devilpanda.auth_service.domain.User;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Object> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        User user = new User(
                registerRequestDto.getLogin(),
                registerRequestDto.getEmail(),
                registerRequestDto.getPassword(),
                registerRequestDto.getIsSubscribed()
        );

        userService.saveUser(user);
        return ResponseEntity.ok("Successful registration");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
        User user = userService.authorizeUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        String jwtToken = jwtService.generateToken(user.getLogin());

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    @PostMapping("/user/change-password")
    public ResponseEntity<Object> changePassword(@RequestBody String password) {
        userService.changePassword(password);
        return ResponseEntity.ok("Password successfully changed");
    }
}
