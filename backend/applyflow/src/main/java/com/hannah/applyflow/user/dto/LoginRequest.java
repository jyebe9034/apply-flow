package com.hannah.applyflow.user.dto;

import com.hannah.applyflow.global.validation.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Request payload for user authentication")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Schema(
            description = "Registered email address of the user.",
            example = "hannah.dev@example.com"
    )
    @NotBlank(message = "Email is required.")
    @Email(message = ValidationConstants.EMAIL_MESSAGE)
    private String email;

    @Schema(
            description = "Account password. Must match the criteria set during registration.",
            example = "SecurePass123!"
    )
    @NotBlank(message = "Password is required.")
    @Pattern(regexp = ValidationConstants.PASSWORD_REGEXP, message = ValidationConstants.PASSWORD_MESSAGE)
    private String password;
}
