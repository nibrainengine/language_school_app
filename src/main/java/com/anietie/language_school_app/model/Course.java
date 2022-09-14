package com.anietie.language_school_app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Course implements Serializable {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    private String type;
    private String level;

    @OneToOne(mappedBy = "course")
    private Results results;

    public Course() {
    }

    public Course(String type, String level, Results results) {
        this.type = type;
        this.level = level;
        this.results = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", results=" + results +
                '}';
    }
}
