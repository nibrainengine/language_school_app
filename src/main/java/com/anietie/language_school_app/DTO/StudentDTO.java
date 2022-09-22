package com.anietie.language_school_app.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class StudentDTO {
    private String name;
    private String address;
    private String phone;
    private String imageUrl;
    private String level;
    private LocalDate dob;
    private String studentNumber;
    private String email;
    private String password;
    private String username;
    private Set<String> role;
}
