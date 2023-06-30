package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.dto.SignUpRequestDto;
import com.dongne.dongnebe.domain.user.service.UserService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Disabled
    void signUpSuccess() throws Exception {
        //given
        SignUpRequestDto request = SignUpRequestDto.builder()
                .userId("user1")
                .username("username1")
                .password("password1")
                .nickname("nickname1")
                .address("address1")
                .build();

        ResponseDto response = ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("User Sign Up Success")
                .build();

        doNothing().when(userService).signUpUsers(any(SignUpRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(response))
        );

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("statusCode", response.getStatusCode()).exists())
                .andExpect(jsonPath("responseMessage", response.getResponseMessage()).exists());

    }
}