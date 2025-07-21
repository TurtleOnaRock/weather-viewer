package com.turtleOnARock.weatherViewer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "Login", unique = true, nullable = false)
    private String login;

    @Column(name = "Password")
    private String password;

    public User(String login, String password){
       this.login = login;
       this.password = password;
    }
}
