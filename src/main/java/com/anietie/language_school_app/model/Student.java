package com.anietie.language_school_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Student implements Serializable {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(nullable = false, updatable = false)
    private long id;
    private String name;
    private String address;
    private String phone;
    private String imageUrl;
    private String level;
    private LocalDate dob;
    private String studentNumber;
    private String email;
    @Transient
    private int age;

    @OneToMany
    private List<Results> result;

    @OneToOne
    @JoinColumn
    private LogIn logIn;

    public LogIn getLogIn() {
        return logIn;
    }


    public Student() {
    }

    public Student(String name, String address, String phone, String imageUrl, String level,
                   LocalDate dob, String studentNumber, String email) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.level = level;
        this.dob = dob;
        this.studentNumber = studentNumber;
        this.result = new ArrayList<>();
        this.logIn = new LogIn();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogIn(LogIn logIn) {
        this.logIn = logIn;
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Results> getResult() {
        return result;
    }

    public void setResult(List<Results> result) {
        this.result = result;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", level='" + level + '\'' +
                ", dob=" + dob +
                ", studentNumber='" + studentNumber + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", result=" + result +
                ", logIn=" + logIn +
                '}';
    }
}
