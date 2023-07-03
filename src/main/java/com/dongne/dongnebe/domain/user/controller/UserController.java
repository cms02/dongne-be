package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.dto.*;
import com.dongne.dongnebe.domain.user.service.UserService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
                                                        @RequestBody @Valid BasicRequestDto basicRequestDto,
                                                        Authentication authentication) {
        ResponseDto result = userService.updateUsersBasic(userId, basicRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/users-password/confirm/{userId}")
    public ResponseEntity<ResponseDto> confirmUsersPassword(@PathVariable String userId,
                                                            @RequestBody @Valid PasswordRequestDto passwordRequestDto,
                                                            Authentication authentication) {
        ResponseDto result = userService.confirmUsersPassword(userId, passwordRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/users-password/{userId}")
    public ResponseEntity<ResponseDto> updateUsersPassword(@PathVariable String userId,
                                                        @RequestBody @Valid PasswordRequestDto passwordRequestDto,
                                                        Authentication authentication) {
        ResponseDto result = userService.updateUsersPassword(userId, passwordRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<ResponseDto> deleteUsers(@PathVariable String userId,
                                                   Authentication authentication) {
        ResponseDto result = userService.deleteUsers(userId, authentication);
        return ResponseEntity.ok().body(result);
    }
}
