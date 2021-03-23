package com.devilpanda.nota.tabula.adapter.jpa;

import com.devilpanda.nota.tabula.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

}
