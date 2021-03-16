package com.devilpanda.nota_tabula.service.api;

import com.devilpanda.nota_tabula.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    /**
     * Находит пользователя по логину
     *
     * @param login - логин пользователя
     * @return - найденный пользователь
     */
    Optional<User> findUserByLogin(String login);

    /**
     * Находит пользователя по емейлу
     *
     * @param email - почта пользователя
     * @return - найденный пользователь
     */
    Optional<User> findUserByEmail(String email);


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
     * @return - сохраненный пользователь
     */
    User saveUser(User user);
}
