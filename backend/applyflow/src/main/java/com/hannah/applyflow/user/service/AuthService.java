package com.hannah.applyflow.user.service;

import com.hannah.applyflow.global.exception.CustomException;
import com.hannah.applyflow.global.response.ErrorCode;
import com.hannah.applyflow.global.security.JwtTokenProvider;
import com.hannah.applyflow.user.User;
import com.hannah.applyflow.user.dto.AuthResponse;
import com.hannah.applyflow.user.dto.LoginRequest;
import com.hannah.applyflow.user.dto.SignupRequest;
import com.hannah.applyflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e ) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }


        String token = jwtTokenProvider.createToken(request.getEmail());
        return new AuthResponse(token);
    }

    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.createUser(request.getEmail(), encodedPassword, request.getName());

        userRepository.save(user);
    }
}
