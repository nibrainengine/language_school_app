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

    public Course() {
    }

    public Course(String type, String level) {
        this.type = type;
        this.level = level;
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
}
