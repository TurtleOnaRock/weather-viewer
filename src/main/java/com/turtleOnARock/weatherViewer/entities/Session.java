package com.turtleOnARock.weatherViewer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "Sessions")
@Setter
@Getter
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "UserId", nullable = false)
    private int userId;

    @Column(name = "ExpiresAt")
    private Time expiresAt;

    public Session(int userId, Time expiresAt){
        this.userId = userId;
        this.expiresAt = expiresAt;
    }
}
