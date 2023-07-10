package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.dto.LoginRequestDto;
import com.dongne.dongnebe.domain.user.dto.LoginResponseDto;
import com.dongne.dongnebe.domain.user.dto.SignUpRequestDto;
import com.dongne.dongnebe.domain.user.service.UserService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.user.IncorrectPasswordException;
import com.dongne.dongnebe.global.exception.user.ResourceAlreadyExistException;
import com.dongne.dongnebe.global.exception.user.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private static final String NICKNAME_ALREADY_EXIST_MSG = "Nickname Already Exist";
    private static final String USERID_ALREADY_EXIST_MSG = "UserId Already Exist";
    private static final String USER_SIGN_UP_SUCCESS_MSG = "User Sign Up Success";
    private static final String USERID_NOT_FOUND_MSG= "UserId Not Found";

    @Test
    @WithMockUser(username = "테스트_최고관리자",roles = {"SUPER"})
    @DisplayName("회원가입성공")
    void 회원가입성공() throws Exception {
        //given
        SignUpRequestDto request = SignUpRequestDto.builder()
                .userId("user1")
                .username("홍길동")
                .password("홍길동123")
                .nickname("홍당무")
                .zoneCode("11200")
                .cityCode("11")
                .build();

        ResponseDto response = ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage(USER_SIGN_UP_SUCCESS_MSG)
                .build();

        doReturn(response).when(userService).signUpUser(any(SignUpRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(response.getStatusCode()))
                .andExpect(jsonPath("$.responseMessage").value(response.getResponseMessage()));
    }

    @Test
    @WithMockUser(username = "테스트_최고관리자",roles = {"SUPER"})
    @DisplayName("회원가입실패_중복닉네임")
    void 회원가입실패_중복닉네임() throws Exception {
        //given
        SignUpRequestDto request = SignUpRequestDto.builder()
                .userId("user1")
                .username("홍길동")
                .password("홍길동123")
                .nickname("중복닉네임")
                .zoneCode("11200")
                .cityCode("11")
                .build();

        doThrow(new ResourceAlreadyExistException(NICKNAME_ALREADY_EXIST_MSG)).when(userService).signUpUser(any(SignUpRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(result ->
                        Assertions.assertThat(getApiResultExceptionClass(result)).isEqualTo(ResourceAlreadyExistException.class))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.responseMessage").value(NICKNAME_ALREADY_EXIST_MSG));
    }

    private Class<? extends Exception> getApiResultExceptionClass(MvcResult result) {
        return Objects.requireNonNull(result.getResolvedException()).getClass();
    }

    @Test
    @WithMockUser(username = "테스트_최고관리자",roles = {"SUPER"})
    @DisplayName("회원가입실패_중복아이디")
    void 회원가입실패_중복아이디() throws Exception {
        //given
        SignUpRequestDto request = SignUpRequestDto.builder()
                .userId("dupliid")
                .username("홍길동")
                .password("홍길동123")
                .nickname("닉네임")
                .zoneCode("11200")
                .cityCode("11")
                .build();

        doThrow(new ResourceAlreadyExistException(USERID_ALREADY_EXIST_MSG)).when(userService).signUpUser(any(SignUpRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(result ->
                        Assertions.assertThat(getApiResultExceptionClass(result)).isEqualTo(ResourceAlreadyExistException.class))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.responseMessage").value(USERID_ALREADY_EXIST_MSG));
    }

    @Test
    @WithMockUser(username = "테스트_최고관리자",roles = {"SUPER"})
    @DisplayName("회원가입실패_파라미터검증에러")
    void 회원가입실패_파라미터검증에러() throws Exception {
        //given
        SignUpRequestDto request = SignUpRequestDto.builder()
                .userId("abcdefghijklmnop10") /*10글자 이내로 입력*/
                .username("홍길동길동홍")/*5글자 이내로 입력*/
                .password("비밀번호비밀번호비밀번호비밀번호")/*15글자 이내로 입력*/
                .nickname("닉네임닉네임닉네임")/*7글자 이내로 입력*/
                .zoneCode("11200")
                .cityCode("11")
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(result ->
                        Assertions.assertThat(getApiResultExceptionClass(result)).isEqualTo(MethodArgumentNotValidException.class))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @WithMockUser(username = "테스트_최고관리자",roles = {"SUPER"})
    @DisplayName("로그인성공")
    void 로그인성공() throws Exception {
        //given
        LoginRequestDto request = LoginRequestDto.builder()
                .userId("userId1")
                .password("password1")
                .build();

        LoginResponseDto response = LoginResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("User Sign Up Success")
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();

        doReturn(response).when(userService).loginUser(any(LoginRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(response.getStatusCode()))
                .andExpect(jsonPath("$.responseMessage").value(response.getResponseMessage()))
                .andExpect(jsonPath("$.accessToken").value(response.getAccessToken()))
                .andExpect(jsonPath("$.refreshToken").value(response.getRefreshToken()));
    }

    @Test
    @WithMockUser(username = "테스트_최고관리자",roles = {"SUPER"})
    @DisplayName("로그인실패_존재하지않는아이디")
    void 로그인실패_존재하지않는아이디() throws Exception {

        //given
        LoginRequestDto request = LoginRequestDto.builder()
                .userId("notexist")
                .password("password1")
                .build();

        doThrow(new ResourceNotFoundException(USERID_NOT_FOUND_MSG)).when(userService).loginUser(any(LoginRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        resultActions
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(result ->
                        Assertions.assertThat(getApiResultExceptionClass(result)).isEqualTo(ResourceNotFoundException.class))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.responseMessage").value(USERID_NOT_FOUND_MSG));
    }

    @Test
    @WithMockUser(username = "테스트_최고관리자",roles = {"SUPER"})
    @DisplayName("로그인실패_틀린비밀번호")
    void 로그인실패_틀린비밀번호() throws Exception {

        //given
        LoginRequestDto request = LoginRequestDto.builder()
                .userId("userId1")
                .password("incorrect")
                .build();

        doThrow(new IncorrectPasswordException("Incorrect Password")).when(userService).loginUser(any(LoginRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(result ->
                        Assertions.assertThat(getApiResultExceptionClass(result)).isEqualTo(IncorrectPasswordException.class))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.responseMessage").value("Incorrect Password"));
    }

    @Test
    @WithMockUser(username = "테스트_최고관리자",roles = {"SUPER"})
    @DisplayName("로그인실패_파라미터검증에러")
    void 로그인실패_파라미터검증에러() throws Exception {
        //given
        LoginRequestDto request = LoginRequestDto.builder()
                .userId("abcdefghijk12345")/*10글자 이내로 입력*/
                .password("1q2w3e4r5tzxcvzxcvxzcv")/*15글자 이내로 입력*/
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(result ->
                        Assertions.assertThat(getApiResultExceptionClass(result)).isEqualTo(MethodArgumentNotValidException.class))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()));
    }
}