package com.devilpanda.auth_service.adapter.user;

import com.devilpanda.auth_service.app.api.IncorrectEmailException;
import com.devilpanda.auth_service.app.api.UserNotFoundException;
import com.devilpanda.auth_service.app.api.UserService;
import com.devilpanda.auth_service.app.api.WrongCredentialsException;
import com.devilpanda.auth_service.domain.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceAdapter implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceAdapter.class);
    private final PasswordEncoder passwordEncoder;
    private final UserFeignClient userFeignClient;

    @Override
    public User findUserByLogin(String login) {
        User user = userFeignClient.findUserByLogin(login);
        if (user == null)
            throw new UserNotFoundException(login);
        return user;
    }

    @Override
    public User authorizeUser(String email, String password) {
        User user = userFeignClient.findUserByEmail(email);
        if (user == null)
            throw new UserNotFoundException(email);
        if (validatePassword(password, user.getPassword()))
            return user;
        else
            throw new WrongCredentialsException(email, password);
    }

    @Override
    public void saveUser(User user) {
        if (!isEmailValid(user.getEmail()))
            throw new IncorrectEmailException(user.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LOGGER.debug("Sending user creation request");
        userFeignClient.createUser(user);
    }

    // ----------------------------------------------
    // = Implementation
    // ----------------------------------------------

    private boolean isEmailValid(String email) {
        return email.matches("[-a-zA-Z.0-9а-яА-Я]+@[a-zA-Z.0-9а-яА-Я]+");
    }

    private Boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
