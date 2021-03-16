package com.devilpanda.nota_tabula.repository;

import com.devilpanda.nota_tabula.model.Role;
import com.devilpanda.nota_tabula.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
