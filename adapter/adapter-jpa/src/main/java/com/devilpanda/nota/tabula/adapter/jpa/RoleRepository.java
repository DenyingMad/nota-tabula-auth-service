package com.devilpanda.nota.tabula.adapter.jpa;

import com.devilpanda.nota.tabula.domain.Role;
import com.devilpanda.nota.tabula.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
