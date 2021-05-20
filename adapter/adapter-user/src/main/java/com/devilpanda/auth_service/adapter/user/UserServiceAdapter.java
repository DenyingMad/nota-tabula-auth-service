package com.devilpanda.auth_service.adapter.user;

import com.devilpanda.auth_service.app.api.IncorrectEmailException;
import com.devilpanda.auth_service.app.api.UserNotFoundException;
import com.devilpanda.auth_service.app.api.UserService;
import com.devilpanda.auth_service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceAdapter implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserFeignClient userFeignClient;

    @Override
    public User findUserByLogin(String login) {
        CredentialDto credential = new CredentialDto();
        credential.setLogin(login);

        User user = userFeignClient.findUserByLogin(credential);
        if (user == null)
            throw new UserNotFoundException(login);
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        CredentialDto credential = new CredentialDto();
        credential.setLogin(login);
        credential.setPassword(password);

        User user = userFeignClient.findUserByLoginAndPassword(credential);
        if (user == null)
            throw new UserNotFoundException(login);
        if (passwordEncoder.matches(password, user.getPassword()))
            return user;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void saveUser(User user) {
        if (!isEmailValid(user.getEmail()))
            throw new IncorrectEmailException(user.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userFeignClient.createUser(user);
    }

    // ----------------------------------------------
    // = Implementation
    // ----------------------------------------------
    private boolean isEmailValid(String email) {
        return email.matches("[-a-zA-Z.0-9а-яА-Я]+@[a-zA-Z.0-9а-яА-Я]+");
    }
}
