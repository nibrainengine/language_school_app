package com.anietie.language_school_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "log_in")
public class LogIn {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "login_id")
    private Long logInId;
    private String password;
    private String username;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "login_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role;

    @JsonIgnore
    @OneToOne(mappedBy = "logIn")
    private Student student;

    public LogIn() {
    }

    @Override
    public String toString() {
        return "LogIn{" +
                "logInId=" + logInId +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", student=" + student +
                '}';
    }
}



