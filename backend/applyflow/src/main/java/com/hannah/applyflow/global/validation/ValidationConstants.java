package com.hannah.applyflow.global.validation;

public class ValidationConstants {

    // Name
    public static final String NAME_REGEXP = "^[a-zA-Z가-힣\\s]{2,50}$";
    public static final String NAME_MESSAGE = "Name must be 2-50 characters and contain only letters.";

    // Email
    public static final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
    public static final String EMAIL_MESSAGE = "Please enter a valid email address.";

    // Password
    public static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
    public static final String PASSWORD_MESSAGE = "Password must be 8-20 characters and include uppercase, lowercase, number, and special character.";

    private ValidationConstants() {}
}
