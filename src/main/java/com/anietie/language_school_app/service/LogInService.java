package com.anietie.language_school_app.service;

import com.anietie.language_school_app.DTO.LogInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class LogInService  {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LogInService(AuthenticationManager authenticationManager ) {
        this.authenticationManager = authenticationManager;
    }


    public String signIn(LogInDTO logInDTO) throws Exception {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInDTO.getUsernameOrEmail(), logInDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "User signed-in successful!.";
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
