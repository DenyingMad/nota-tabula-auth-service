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
     * Поиск пользователя по email
     *
     * @param email - почтовый адрес пользователя
     * @return - найденный пользователь
     */
    User authorizeUser(String email, String password);

    /**
     * Сохранение пользователя
     *
     * @param user - пользователь, которого нужно сохранить
     */
    void saveUser(User user);

    /**
     * Генерируется новый хешированный пароль и отправляется в user-service
     *
     * @param password - пароль пользователя
     */
    void changePassword(String password);
}
