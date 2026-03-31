package com.hannah.applyflow.user.dto;

import com.hannah.applyflow.global.validation.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email is required.")
    @Email(message = ValidationConstants.EMAIL_MESSAGE)
    private String email;

    @NotBlank(message = "Password is required.")
    @Pattern(regexp = ValidationConstants.PASSWORD_REGEXP, message = ValidationConstants.PASSWORD_MESSAGE)
    private String password;
}
