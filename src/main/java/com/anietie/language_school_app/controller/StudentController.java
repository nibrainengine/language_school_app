package com.anietie.language_school_app.controller;

import com.anietie.language_school_app.model.Student;
import com.anietie.language_school_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDate;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> allStudent = studentService.getStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
            @RequestParam(required = false) String studentNumber) {
        Student updatedStudent = studentService.updateStudent(id,
                name, address, phone, imageUrl, level, dob, studentNumber);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
        String deleteResponse = studentService.deletestudent(studentId);
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }



}
