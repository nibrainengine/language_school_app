package com.anietie.language_school_app.service;

import com.anietie.language_school_app.model.Student;
import com.anietie.language_school_app.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student registerStudent(Student student) {
        boolean exists = studentRepo.findAll().contains(student);
        if(!exists) {
            Student newStudent = studentRepo.save(student);
            return newStudent;
        }
        throw new IllegalStateException("student already exists!");
    }

    public Student getStudentById(Long id) {
        boolean exists = studentRepo.existsById(id);
        if(exists) {
            Student student = studentRepo.findById(id).get();
            System.out.println(student);
            return student;
        }
        throw new IllegalStateException("student with Id number " + id + " does not exist");
    }
}
