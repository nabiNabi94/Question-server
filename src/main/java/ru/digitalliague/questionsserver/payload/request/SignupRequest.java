package ru.digitalliague.questionsserver.payload.request;

import lombok.Data;
import ru.digitalliague.questionsserver.annotations.PasswordMatchers;
import ru.digitalliague.questionsserver.annotations.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatchers
public class SignupRequest {

    @Email(message = "Here should have email format")
    @NotBlank(message = "Email is requirement")
    @ValidEmail
    private String email;
    @NotBlank(message = "Name is requirement")
    @NotEmpty(message = "Pleas enter your name")
    private String name;
    @NotBlank(message = "Username is requirement")
    @NotEmpty(message = "Pleas enter your username")
    private String username;
    @NotBlank(message = "Password is requirement")
    @NotEmpty(message = "Pleas enter your password")
    @Size(min = 6)
    private String password;
    private String conformPassword;

}
