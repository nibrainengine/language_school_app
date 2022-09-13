package com.anietie.language_school_app.repository;

import com.anietie.language_school_app.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Long> {

    Optional<Course> findByType(String type);
}
