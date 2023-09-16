package com.dongne.dongnebe.domain.user.service;

import com.dongne.dongnebe.domain.user.dto.request.SignUpRequestDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceAlreadyExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @DisplayName("회원가입_성공")
    @Test
    void signUpUser() {

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
}
