package com.dongne.dongnebe.domain.user.service;

import com.dongne.dongnebe.domain.user.dto.request.SignUpRequestDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @DisplayName("회원 가입")
    @Test
    void signUpUser() {

        //given
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        SignUpRequestDto requestDto = signUprequest();
        String encryptedPw = encoder.encode(requestDto.getPassword());

        doReturn(User.builder()
                .userId("cms02")
                .username("추민석")
                .password(encryptedPw)
                .nickname("추추민석")
                .build())
                .when(userRepository)
                .save(any(User.class));

        //when
        ResponseDto responseDto = userService.signUpUser(requestDto);

        //then
        Assertions.assertThat(encoder.matches(requestDto.getPassword(), encryptedPw)).isTrue();
        Assertions.assertThat(responseDto.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        //verify
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(any(String.class));


    }

    private SignUpRequestDto signUprequest() {
        return SignUpRequestDto.builder()
                .userId("cms02")
                .username("추민석")
                .password("password1234")
                .nickname("추추민석")
                .zoneCode("11")
                .cityCode("11170")
                .build();
    }
}
