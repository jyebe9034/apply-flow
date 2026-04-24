package com.hannah.applyflow.user.dto;

import com.hannah.applyflow.global.validation.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Schema(description = "Request payload for user registration")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @Schema(
            description = "Full name of the user. Only letters and spaces are allowed.",
            example = "Jihye Lim"
    )
    @NotBlank(message = "Name is required.")
    @Pattern(regexp = ValidationConstants.NAME_REGEXP, message = ValidationConstants.NAME_MESSAGE)
    private String name;

    @Schema(
            description = "Unique email address used for login and notifications.",
            example = "hannah.dev@example.com"
    )
    @NotBlank(message = "Email is required.")
    @Email(message = ValidationConstants.EMAIL_MESSAGE)
    private String email;

    @Schema(
            description = "Password for the account. Must be at least 8 characters long and include a mix of letters, numbers, and special characters.",
            example = "SecurePass123!"
    )
    @NotBlank(message = "Password is required.")
    @Pattern(regexp = ValidationConstants.PASSWORD_REGEXP, message = ValidationConstants.PASSWORD_MESSAGE)
    private String password;
}
