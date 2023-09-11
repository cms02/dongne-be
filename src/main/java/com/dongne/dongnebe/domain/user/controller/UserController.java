package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.dto.UserRankingDto;
import com.dongne.dongnebe.domain.user.dto.request.*;
import com.dongne.dongnebe.domain.user.dto.response.*;
import com.dongne.dongnebe.domain.user.service.UserService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/sign-up")
    public ResponseEntity<ResponseDto> signUpUser(@RequestBody @Valid SignUpRequestDto requestDto) {
        ResponseDto result = userService.signUpUser(requestDto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        LoginResponseDto result = userService.loginUser(loginRequestDto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/user/reissue")
    public ResponseEntity<ResponseDto> reissue(Authentication authentication) {
        ReissueResponseDto result = userService.reissue(authentication);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/user/logout")
    public ResponseEntity<ResponseDto> logoutUser(Authentication authentication) {
        ResponseDto result = userService.logoutUser(authentication);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/user-basic/{userId}")
    public ResponseEntity<ResponseDto> findUserBasic(@PathVariable String userId,
                                                        Authentication authentication) {
        UsersBasicResponseDto result = userService.findUserBasic(userId, authentication);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/user-main")
    public ResponseEntity<ResponseDto> findUserMain(Authentication authentication, Pageable pageable) {
        UsersMainResponseDto result = userService.findUserMain(authentication, pageable);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/user-basic/{userId}")
    public ResponseEntity<ResponseDto> updateUserBasic(@PathVariable String userId,
                                                        @RequestPart MultipartFile file,
                                                        @RequestPart @Valid BasicRequestDto basicRequestDto,
                                                        Authentication authentication) {
        ResponseDto result = userService.updateUserBasic(userId, file, basicRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/user-password/confirm/{userId}")
    public ResponseEntity<ResponseDto> confirmUserPassword(@PathVariable String userId,
                                                            @RequestBody @Valid PasswordRequestDto passwordRequestDto,
                                                            Authentication authentication) {
        ResponseDto result = userService.confirmUserPassword(userId, passwordRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/user-password/{userId}")
    public ResponseEntity<ResponseDto> updateUserPassword(@PathVariable String userId,
                                                        @RequestBody @Valid PasswordRequestDto passwordRequestDto,
                                                        Authentication authentication) {
        ResponseDto result = userService.updateUserPassword(userId, passwordRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/user/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable String userId,
                                                   Authentication authentication) {
        ResponseDto result = userService.deleteUser(userId, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/user/ranking")
    public ResponseEntity<ResponseDto> findUserRanking(@RequestBody UserRankingRequestDto userRankingRequestDto,
                                                       Pageable pageable) {
        UserRankingResponseDto result = userService.findUserRanking(userRankingRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }
}
