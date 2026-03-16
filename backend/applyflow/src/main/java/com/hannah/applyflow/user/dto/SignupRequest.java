package com.hannah.applyflow.user.dto;

import com.hannah.applyflow.global.validation.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "Name is required.")
    @Pattern(regexp = ValidationConstants.NAME_REGEXP, message = ValidationConstants.NAME_MESSAGE)
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = ValidationConstants.EMAIL_MESSAGE)
    private String email;

    @NotBlank(message = "Password is required.")
    @Pattern(regexp = ValidationConstants.PASSWORD_REGEXP, message = ValidationConstants.PASSWORD_MESSAGE)
    private String password;
}
