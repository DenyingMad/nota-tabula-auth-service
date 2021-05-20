package com.devilpanda.auth_service.app.api;

import com.devilpanda.auth_service.domain.User;

import java.util.Optional;

public interface UserService {

    /**
     * Находит пользователя по логину
     *
     * @param login - логин пользователя
     * @return - найденный пользователь
     */
    User findUserByLogin(String login);

    /**
     * Находит пользователя по логину и хэшированному паролю
     *
     * @param login    - логин пользователя
     * @param password - пароль пользователя
     * @return - найденный пользователь
     */
    User findUserByLoginAndPassword(String login, String password);

    /**
     * Сохранение пользователя
     *
     * @param user - пользователь, которого нужно сохранить
     */
    void saveUser(User user);
}
