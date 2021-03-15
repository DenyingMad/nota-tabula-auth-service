package com.devilpanda.nota_tabula.security;

import com.devilpanda.nota_tabula.errors.IncorrectEmailException;
import com.devilpanda.nota_tabula.errors.UserAlreadyExistException;
import com.devilpanda.nota_tabula.model.User;
import com.devilpanda.nota_tabula.security.dto.LoginFormDto;
import com.devilpanda.nota_tabula.security.dto.RegisterFormDto;
import com.devilpanda.nota_tabula.security.jwt.JwtProvider;
import com.devilpanda.nota_tabula.security.jwt.JwtResponse;
import com.devilpanda.nota_tabula.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody RegisterFormDto registerFormDto) {
        User user = new User();
        user.setLogin(registerFormDto.getLogin());
        user.setEmail(registerFormDto.getEmail());
        user.setPassword(registerFormDto.getPassword());

        try {
            userService.saveUser(user);
            return authenticateUser(mapRegisterFormToLoginForm(registerFormDto));
        } catch (UserAlreadyExistException | IncorrectEmailException e) {
            String warnMessage = String.format("Can't register new user -> Message: %s", e.getMessage());
            LOGGER.warn(warnMessage);
            return ResponseEntity.status(418).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginFormDto loginFormDto) {
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(loginFormDto.getEmail(), loginFormDto.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtProvider.generateToken(auth);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    private LoginFormDto mapRegisterFormToLoginForm(RegisterFormDto registerFormDto) {
        LoginFormDto loginFormDto = new LoginFormDto();
        loginFormDto.setEmail(registerFormDto.getEmail());
        loginFormDto.setPassword(registerFormDto.getPassword());
        return loginFormDto;
    }
}