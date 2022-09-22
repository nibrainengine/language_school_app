package com.anietie.language_school_app.service;

import com.anietie.language_school_app.DTO.JwtResponse;
import com.anietie.language_school_app.DTO.LogInDTO;
import com.anietie.language_school_app.security.jwt.JwtUtils;
import com.anietie.language_school_app.security.services.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LogInService  {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public LogInService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public JwtResponse signIn(LogInDTO logInDTO) throws Exception {
        try{
            System.out.println(logInDTO.getUsernameOrEmail() + " "+ logInDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInDTO.getUsernameOrEmail(), logInDTO.getPassword()));
            System.out.println("here");

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            System.out.println("here2");

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            System.out.println("here3");
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            System.out.println(jwt);

            return new JwtResponse(
                    jwt, userDetails.getId(), userDetails.getUsername(),
                    userDetails.getEmail(), roles
            );
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
