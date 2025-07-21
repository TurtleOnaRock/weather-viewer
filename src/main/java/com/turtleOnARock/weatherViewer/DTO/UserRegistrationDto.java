package com.turtleOnARock.weatherViewer.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRegistrationDto {

    @Email(message = "Wrong email format.")
    private String email;

    @Size(min = 4, max = 20, message = "Password should be between 4 and 20 characters.")
    private String password;

    private String repeatedPassword;
}
