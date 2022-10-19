package com.anietie.language_school_app.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResultDTO {
    private int examScore;
    private LocalDate examDate;
    private Long studentId;
    private Long courseId;
}
