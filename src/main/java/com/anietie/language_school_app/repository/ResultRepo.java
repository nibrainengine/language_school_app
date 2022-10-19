package com.anietie.language_school_app.repository;

import com.anietie.language_school_app.model.Course;
import com.anietie.language_school_app.model.Results;
import com.anietie.language_school_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Results, Long> {

    Boolean existsByStudentAndCourse(Student student, Course course);
}
