package com.turtleOnARock.weatherViewer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "AppSessions")
@Setter
@Getter
@NoArgsConstructor
public class AppSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "UserId", nullable = false)
    private int userId;

    @Column(name = "ExpiresAt")
    private LocalDateTime expiresAt;

    public AppSession(int userId, LocalDateTime expiresAt){
        this.userId = userId;
        this.expiresAt = expiresAt;
    }
}
