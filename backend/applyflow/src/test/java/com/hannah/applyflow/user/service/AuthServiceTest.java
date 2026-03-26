package com.hannah.applyflow.user.service;

import com.hannah.applyflow.global.exception.CustomException;
import com.hannah.applyflow.global.response.ErrorCode;
import com.hannah.applyflow.global.security.JwtTokenProvider;
import com.hannah.applyflow.user.User;
import com.hannah.applyflow.user.dto.AuthResponse;
import com.hannah.applyflow.user.dto.LoginRequest;
import com.hannah.applyflow.user.dto.SignupRequest;
import com.hannah.applyflow.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthenticationManager manager;

    @InjectMocks
    private AuthService authService;

    @Test
    void signup_success() {
        // given
        SignupRequest request = new SignupRequest("TestManager", "TestManager@test.com", "Password1!");
        given(userRepository.existsByEmail(request.getEmail())).willReturn(false);
        given(passwordEncoder.encode(request.getPassword())).willReturn("encodedPassword");

        // when
        authService.signup(request);

        // then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void signup_duplicateEmail_throwsException() {
        //given
        SignupRequest request = new SignupRequest("Hannah", "hannah@gmail.com", "Password1");
        given(userRepository.existsByEmail(request.getEmail())).willReturn(true);

        // when & then
        assertThatThrownBy(() -> authService.signup(request))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.DUPLICATE_EMAIL.getMessage());
    }

    @Test
    void login_success() {
        // given
        LoginRequest request = new LoginRequest("TestManager@test.com", "Password1");
        given(jwtTokenProvider.createToken(request.getEmail())).willReturn("accessToken");

        // when
        AuthResponse response = authService.login(request);

        // then
        assertThat(response.getAccessToken()).isEqualTo("accessToken");
        verify(manager, times(1)).authenticate(any());
    }

    @Test
    void login_invalidCredential_throwException() {
        // given
        LoginRequest request = new LoginRequest("hannah@gmail.com", "wrongPassword");
        given(manager.authenticate(any()))
                .willThrow(new BadCredentialsException("Bad credentials"));

        // when & then
        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(BadCredentialsException.class);
    }
}
