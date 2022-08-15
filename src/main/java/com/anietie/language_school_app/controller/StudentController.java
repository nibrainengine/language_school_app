package com.anietie.language_school_app.controller;

import com.anietie.language_school_app.model.Student;
import com.anietie.language_school_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student newStudent = studentService.registerStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity<Student> getStudentStudentById(@PathVariable("studentId") Long id) {
        Student outputStudent = studentService.getStudentById(id);
        return new ResponseEntity<>(outputStudent, HttpStatus.OK);
    }


}
