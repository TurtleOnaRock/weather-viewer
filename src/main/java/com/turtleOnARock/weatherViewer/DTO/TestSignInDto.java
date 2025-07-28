package com.turtleOnARock.weatherViewer.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TestSignInDto {
    private String login;
    private int sessionId;
    private LocalDateTime expiresAt;
}
