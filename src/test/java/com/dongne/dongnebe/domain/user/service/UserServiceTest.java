package com.dongne.dongnebe.domain.user.service;

import com.dongne.dongnebe.domain.user.dto.request.LoginRequestDto;
import com.dongne.dongnebe.domain.user.dto.request.SignUpRequestDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.jwt.JwtTokenProvider;
import com.dongne.dongnebe.domain.user.redis.RedisService;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceAlreadyExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private RedisService redisService;

    @DisplayName("회원가입_성공")
    @Test
    void signUpUser_Success() {

        //given
        SignUpRequestDto requestDto = signUpRequest();

        when(userRepository.findByUserId(requestDto.getUserId())).thenReturn(Optional.empty());
        when(userRepository.findByNickname(requestDto.getNickname())).thenReturn(Optional.empty());

        //when
        ResponseDto responseDto = userService.signUpUser(requestDto);

        //then
        Assertions.assertThat(responseDto.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        //verify
        verify(userRepository, times(1)).save(any(User.class));

    }

    @DisplayName("회원가입_실패_중복_ID")
    @Test
    void signUpUser_Fail_Same_UserId() {
        //given
        SignUpRequestDto requestDto = signUpRequest();

        when(userRepository.findByUserId(requestDto.getUserId())).thenReturn(Optional.of(User.builder().build()));

        //when
        ResourceAlreadyExistException exception = assertThrows(ResourceAlreadyExistException.class,
                () -> userService.signUpUser(requestDto));

        //then
        Assertions.assertThat(exception.getMessage()).isEqualTo("User Id Already Exist");
    }

    @DisplayName("회원가입_실패_중복_Nickname")
    @Test
    void signUpUser_Fail_Same_Nickname() {
        //given
        SignUpRequestDto requestDto = signUpRequest();

        when(userRepository.findByNickname(requestDto.getNickname())).thenReturn(Optional.of(User.builder().build()));

        //when
        ResourceAlreadyExistException exception = assertThrows(ResourceAlreadyExistException.class,
                () -> userService.signUpUser(requestDto));

        //then
        Assertions.assertThat(exception.getMessage()).isEqualTo("Nickname Already Exist");
    }

    @DisplayName("로그인_성공")
    @Test
    void LoginUser_Success() {

        //given
        LoginRequestDto requestDto = loginRequest();
        String encPwd = passwordEncoder.encode(requestDto.getPassword());
        when(userRepository.findByUserId(requestDto.getUserId())).thenReturn(Optional.of(User.builder()
                .userId(requestDto.getUserId())
                .password(encPwd)
                .build()));

        when(passwordEncoder.matches(requestDto.getPassword(), encPwd)).thenReturn(true);

        Authentication authentication = mock(Authentication.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(authenticationManagerBuilder.getObject()).thenReturn(authenticationManager);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        when(jwtTokenProvider.responseAccessToken(any())).thenReturn(eq("String by matcher"));
        when(jwtTokenProvider.responseRefreshToken(any())).thenReturn(eq("String by matcher"));

        //when
        ResponseDto responseDto = userService.loginUser(requestDto);


        //then
        Assertions.assertThat(responseDto.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(responseDto.getResponseMessage()).isEqualTo("Login Success");

        //verify
        verify(userRepository, times(1)).findByUserId(anyString());

    }

    private SignUpRequestDto signUpRequest() {
        return SignUpRequestDto.builder()
                .userId("cms02")
                .username("추민석")
                .password("password1234")
                .nickname("추추민석")
                .zoneCode("11")
                .cityCode("11170")
                .build();
    }

    private LoginRequestDto loginRequest() {
        return LoginRequestDto.builder()
                .userId("cms02")
                .password("password1234")
                .build();
    }
}
