package com.hannah.applyflow.user.controller;

import com.hannah.applyflow.global.security.CustomUserDetails;
import com.hannah.applyflow.user.User;
import com.hannah.applyflow.user.dto.UserResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return new UserResponse(user.getEmail(), user.getName());
    }
}
