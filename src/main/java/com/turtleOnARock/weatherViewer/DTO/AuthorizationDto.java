package com.turtleOnARock.weatherViewer.DTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDto {

    @Email(message = "Wrong email format.")
    private String login;

    private String password;
}
