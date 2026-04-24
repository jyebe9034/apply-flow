package com.hannah.applyflow.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "User profile information for the authenticated account")
@Getter
@AllArgsConstructor
public class UserResponse {

    @Schema(description = "User's registered email address", example = "jihye.dev@example.com")
    private String email;

    @Schema(description = "User's full name", example = "Jihye Lim")
    private String name;
}
