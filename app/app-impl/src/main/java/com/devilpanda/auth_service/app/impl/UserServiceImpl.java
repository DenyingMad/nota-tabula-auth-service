package com.devilpanda.auth_service.app.impl;

import com.devilpanda.auth_service.adapter.jpa.RoleRepository;
import com.devilpanda.auth_service.adapter.jpa.UserRepository;
import com.devilpanda.auth_service.app.api.IncorrectEmailException;
import com.devilpanda.auth_service.app.api.UserAlreadyExistException;
import com.devilpanda.auth_service.app.api.UserService;
import com.devilpanda.auth_service.domain.Role;
import com.devilpanda.auth_service.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static com.devilpanda.auth_service.domain.RoleName.ROLE_USER;
import static java.util.Collections.singleton;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> {
            throw new EntityNotFoundException("Can't find user with login " + login);
        });
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        if (userAlreadyExist(user))
            throw new UserAlreadyExistException(
                    String.format("Email %s or Login %s are already taken", user.getEmail(), user.getLogin()));
        if (!validateEmail(user.getEmail()))
            throw new IncorrectEmailException(
                    String.format("Email %s is not in the correct format", user.getEmail()));

        Role userRole = roleRepository.findByName(ROLE_USER).orElseThrow();
        user.setRoles(singleton(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.saveAndFlush(user);
    }

    private boolean userAlreadyExist(User user) {
        return userRepository.findByLogin(user.getLogin()).isPresent()
                || userRepository.findByEmail(user.getEmail()).isPresent();
    }

    private boolean validateEmail(String email) {
        return email.matches("[-a-zA-Z.0-9а-яА-Я]+@[a-zA-Z.0-9а-яА-Я]+");
    }
}
