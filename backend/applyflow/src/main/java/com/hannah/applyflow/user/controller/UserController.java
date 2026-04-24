package com.hannah.applyflow.user.controller;

import com.hannah.applyflow.global.security.CustomUserDetails;
import com.hannah.applyflow.user.User;
import com.hannah.applyflow.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API", description = "Endpoints for managing user profiles and account information")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Operation(
            summary = "Get current user profile",
            description = "Retrieves the profile information of the currently authenticated user based on the security context."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user profile"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User is not logged in")
    })
    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return new UserResponse(user.getEmail(), user.getName());
    }
}
