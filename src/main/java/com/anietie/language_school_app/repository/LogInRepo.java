package com.anietie.language_school_app.repository;

import com.anietie.language_school_app.model.LogIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogInRepo extends JpaRepository<LogIn, Long> {

    Optional<LogIn> findByUsername(String username);
    Boolean existsByUsername(String username);
}
