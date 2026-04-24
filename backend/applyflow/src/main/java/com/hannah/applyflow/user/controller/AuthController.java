package com.hannah.applyflow.user.controller;

import com.hannah.applyflow.global.response.ApiResponse;
import com.hannah.applyflow.user.dto.AuthResponse;
import com.hannah.applyflow.user.dto.LoginRequest;
import com.hannah.applyflow.user.dto.SignupRequest;
import com.hannah.applyflow.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API", description = "Endpoints for user authentication and account registration")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "User Login",
            description = "Authenticates a user and returns a JWT token for accessing secured endpoints."
    )
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse data = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", data));
    }

    @Operation(
            summary = "User Registration",
            description = "Creates a new user account with the provided email and password."
    )
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@Valid @RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Registration completed successfully."));
    }
}
