package com.anietie.language_school_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Results implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private int examScore;
    private LocalDate examDate;
    @ManyToOne
    private Student student;
    @OneToMany
    private List<Course> course;

    public Results() {
    }

    public Results(int examScore, LocalDate examDate) {
        this.examScore = examScore;
        this.examDate = examDate;
        this.student = new Student();
        this.course = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getExamScore() {
        return examScore;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Results{" +
                "id=" + id +
                ", examScore=" + examScore +
                ", examDate=" + examDate +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
