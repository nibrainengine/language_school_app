package com.anietie.language_school_app.repository;

import com.anietie.language_school_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {

Optional<Student> findByName(String name);
Optional<Student> findByEmail(String email);
Boolean existsByEmail(String email);
}
