package com.anietie.language_school_app.controller;

import com.anietie.language_school_app.DTO.LogInDTO;
import com.anietie.language_school_app.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
public class LogInController {

    private final LogInService logInService;

    @Autowired
    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }

    @PostMapping(path = "/signIn")
    public ResponseEntity<String> signIn(@RequestBody LogInDTO logInDTO ) {
        try {
            String AuthenticateResponse = logInService.signIn(logInDTO);
            return new ResponseEntity<>(AuthenticateResponse, HttpStatus.OK);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect Password");
        }
    }

}
