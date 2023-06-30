package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.dto.LoginRequestDto;
import com.dongne.dongnebe.domain.user.dto.LoginResponseDto;
import com.dongne.dongnebe.domain.user.dto.SignUpRequestDto;
import com.dongne.dongnebe.domain.user.service.UserService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users/sign-up")
    public ResponseEntity<ResponseDto> signUpUsers(@RequestBody @Valid SignUpRequestDto requestDto) {
        //        Dto valid check 추가해야함
        userService.signUpUsers(requestDto);
        return ResponseEntity.ok().body(ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("User Sign Up Success")
                .build());
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<ResponseDto> loginUsers(@RequestBody LoginRequestDto loginRequestDto) {
//        Dto valid check 추가해야함

        LoginResponseDto result = userService.loginUsers(loginRequestDto);
        return ResponseEntity.ok().body(result);
    }
}
