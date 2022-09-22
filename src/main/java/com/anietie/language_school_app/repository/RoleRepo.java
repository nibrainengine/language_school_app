package com.anietie.language_school_app.repository;

import com.anietie.language_school_app.model.ERole;
import com.anietie.language_school_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
