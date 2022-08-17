package com.anietie.language_school_app.service;

import com.anietie.language_school_app.model.Student;
import com.anietie.language_school_app.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student registerStudent(Student student) {
        boolean exists = studentRepo.findByName(student.getName()).isPresent();
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

    public List<Student> getStudent() {
        List<Student> allStudent = studentRepo.findAll();
        return allStudent;
    }

    @Transactional
    public Student updateStudent(Long id,
                                 String name, String address,
                                 String phone, String imageUrl,
                                 String level, LocalDate dob,
                                 String studentNumber) {
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

    public String deletestudent(Long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student does not exist"));
        studentRepo.delete(student);
        if(!studentRepo.existsById(studentId)) {
            return "student with id: " + studentId + " has been deleted";
        }
        else
            throw new IllegalStateException("student cannot be deleted");
    }
}
