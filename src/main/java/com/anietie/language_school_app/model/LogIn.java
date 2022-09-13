package com.anietie.language_school_app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class LogIn {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long logInId;
    private String password;
    private String username;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> role;

    public LogIn() {
    }

    public LogIn(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public Long getLogInId() {
        return logInId;
    }

    public void setLogInId(Long logInId) {
        this.logInId = logInId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "LogIn{" +
                "logInId=" + logInId +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}



