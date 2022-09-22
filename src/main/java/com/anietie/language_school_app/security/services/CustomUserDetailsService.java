package com.anietie.language_school_app.security.services;

import com.anietie.language_school_app.model.LogIn;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.anietie.language_school_app.repository.LogInRepo;
import com.anietie.language_school_app.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final LogInRepo logInRepo;
    private final StudentRepo studentRepo;

    @Autowired
    public CustomUserDetailsService(LogInRepo logInRepo, StudentRepo studentRepo) {
        this.logInRepo = logInRepo;
        this.studentRepo = studentRepo;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Boolean usernameExists = logInRepo.existsByUsername(usernameOrEmail);
        Boolean emailExists = studentRepo.existsByEmail(usernameOrEmail);
        LogIn logIn = null;
        if(!(usernameExists || emailExists)) {
            throw new UsernameNotFoundException ("username or email does not exist");
        }
        if(usernameExists) {

            logIn = logInRepo.findByUsername(usernameOrEmail).get();
        }
        if(emailExists) {
            logIn = studentRepo.findByEmail(usernameOrEmail).get().getLogIn();
        }
        return CustomUserDetails.build(logIn);
    }
}
