package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.dto.LoginRequestDto;
import com.dongne.dongnebe.domain.user.dto.LoginResponseDto;
import com.dongne.dongnebe.domain.user.dto.SignUpRequestDto;
import com.dongne.dongnebe.domain.user.dto.UpdateRequestDto;
import com.dongne.dongnebe.domain.user.service.UserService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users/sign-up")
    public ResponseEntity<ResponseDto> signUpUsers(@RequestBody @Valid SignUpRequestDto requestDto) {
        ResponseDto result = userService.signUpUsers(requestDto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<ResponseDto> loginUsers(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        LoginResponseDto result = userService.loginUsers(loginRequestDto);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/users-basic/{userId}")
    public ResponseEntity<ResponseDto> updateUsersBasic(@PathVariable String userId,
                                                        @RequestBody @Valid UpdateRequestDto updateRequestDto,
                                                        Authentication authentication) {

        ResponseDto result = userService.updateUsersBasic(userId, updateRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<ResponseDto> deleteUsers(@PathVariable String userId,
                                                   Authentication authentication) {
        ResponseDto result = userService.deleteUsers(userId, authentication);
        return ResponseEntity.ok().body(result);
    }
}
