package com.devilpanda.auth_service.adapter.jpa;

import com.devilpanda.auth_service.domain.Role;
import com.devilpanda.auth_service.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
