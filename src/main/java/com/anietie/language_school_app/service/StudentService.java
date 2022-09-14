package com.anietie.language_school_app.service;

import com.anietie.language_school_app.DTO.StudentDTO;
import com.anietie.language_school_app.model.LogIn;
import com.anietie.language_school_app.model.Role;
import com.anietie.language_school_app.model.Student;
import com.anietie.language_school_app.repository.LogInRepo;
import com.anietie.language_school_app.repository.RoleRepo;
import com.anietie.language_school_app.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final LogInRepo logInRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo,
                          PasswordEncoder passwordEncoder,
                          RoleRepo roleRepo,
                          LogInRepo logInRepo) {
        this.studentRepo = studentRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.logInRepo = logInRepo;
    }

    public Student registerStudent(StudentDTO studentDTO) throws Exception{
        try{
            boolean exists = studentRepo.findByName(studentDTO.getName()).isPresent();
            if(!exists) {
                Student newStudent = new Student();
                LogIn newLogin = new LogIn();
                newStudent.setName(studentDTO.getName());
                System.out.println(newStudent.getName());
                newStudent.setAddress(studentDTO.getAddress());
                newStudent.setPhone(studentDTO.getPhone());
                newStudent.setImageUrl(studentDTO.getImageUrl());
                newStudent.setEmail(studentDTO.getEmail());
                newStudent.setDob(studentDTO.getDob());
                System.out.println(newStudent.getEmail());
                newLogin.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
                newLogin.setUsername(studentDTO.getUsername());
                setStudentRole(newLogin);
                System.out.println(newLogin.getPassword());
                System.out.println(List.of(newStudent));
                LogIn logIn = logInRepo.save(newLogin);
                System.out.println("here is it");
                newStudent.setLogIn(logIn);
                return studentRepo.save(newStudent);
            }
            throw new DuplicateKeyException("student already exists!");
        }
        catch (DuplicateKeyException e) {
            throw new DuplicateKeyException(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }

    }

    private void setStudentRole(LogIn logIn) {
        Role role = roleRepo.findByName("student").get();
        logIn.setRole(Collections.singleton(role));
    }

    public Student getStudentById(Long id) throws Exception {
        try{
            boolean exists = studentRepo.existsById(id);
            if(exists) {
                Student student = studentRepo.findById(id).get();
                return student;
            }
            throw new IllegalStateException("student with Id number " + id + " does not exist");
        }
        catch (Exception e) {
            throw  new Exception(e.getMessage());
        }

    }

    public List<Student> getStudent() throws Exception {
        try {
            List<Student> allStudent = studentRepo.findAll();
            return allStudent;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Student updateStudent (Long id,
                                 String name, String address,
                                 String phone, String imageUrl,
                                 String level, LocalDate dob,
                                 String studentNumber) throws Exception {
        try {
            Boolean exists = studentRepo.existsById(id);
            if(exists) {
                Student student = studentRepo.findById(id).get();
                if(name != null)
                    student.setName(name);
                if(phone != null)
                    student.setPhone(phone);
                if(imageUrl != null)
                    student.setImageUrl(imageUrl);
                if(address != null)
                    student.setAddress(address);
                if(studentNumber != null)
                    student.setStudentNumber(studentNumber);
                if(level != null)
                    student.setLevel(level);
                if(dob != null)
                    student.setDob(dob);
                return student;
            }
            throw new IllegalStateException("no such student Available");
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String deletestudent(Long studentId) throws Exception {
        try{
            Student student = studentRepo.findById(studentId).orElseThrow(
                    () -> new IllegalStateException("Student does not exist"));
            studentRepo.delete(student);
            if(!studentRepo.existsById(studentId)) {
                return "student with id: " + studentId + " has been deleted";
            }
            else
                throw new IllegalStateException("student cannot be deleted");
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
