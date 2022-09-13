package com.anietie.language_school_app.service;

import com.anietie.language_school_app.model.LogIn;
import com.anietie.language_school_app.model.Role;
import com.anietie.language_school_app.model.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import com.anietie.language_school_app.repository.LogInRepo;
import com.anietie.language_school_app.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
        return new org.springframework.security.core.userdetails.User(
                logIn.getUsername(), logIn.getPassword(), mapRolesToAuthorities(logIn.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(
                role.getName())).collect(Collectors.toList());
    }

}
