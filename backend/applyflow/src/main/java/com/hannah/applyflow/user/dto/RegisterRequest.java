package com.hannah.applyflow.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    private String password;
    private String name;
}
