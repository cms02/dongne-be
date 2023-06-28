package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.dto.JoinRequestDto;
import com.dongne.dongnebe.domain.user.dto.LoginRequestDto;
import com.dongne.dongnebe.domain.user.dto.LoginResponseDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.user.jwt.JwtTokenProvider;
import com.dongne.dongnebe.domain.user.redis.RedisService;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.user.IncorrectPasswordException;
import com.dongne.dongnebe.global.exception.user.UserIdAlreadyExistException;
import com.dongne.dongnebe.global.exception.user.UserIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @PostMapping("/api/users/join")
    public ResponseEntity<ResponseDto> joinUsers(@RequestBody JoinRequestDto requestDto) {
//        Dto valid check 추가해야함
        validateUser(requestDto);

        /*ID 중복 처리*/
        userRepository.save(
                User.builder()
                        .userId(requestDto.getUserId())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .nickname(requestDto.getNickname())
                        .address(requestDto.getAddress())
                        .role(Role.USER)
                        .build());
        return ResponseEntity.ok().body(ResponseDto.builder()
                .statusCode(200)
                .responseMessage("User Joined Success")
                .build());
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<ResponseDto> loginUsers(@RequestBody LoginRequestDto loginRequestDto) {
//        Dto valid check 추가해야함
        User user = userRepository.findByUserId(loginRequestDto.getUserId())
                .orElseThrow(() -> new UserIdNotFoundException("User Id Not Found"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect Password");
        }

        String accessToken = jwtTokenProvider.responseAccessToken(user);
        String refreshToken = jwtTokenProvider.responseRefreshToken(user);
        redisService.setValues(user.getUserId(), refreshToken,
                JwtTokenProvider.REFRESH_TOKEN_EXPIRATION_TIME, TimeUnit.MILLISECONDS);
        LoginResponseDto result = LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .statusCode(200)
                .responseMessage("Login Success")
                .build();

        return ResponseEntity.ok().body(result);
    }

    private void validateUser(JoinRequestDto requestDto) {
        checkUserId(requestDto.getUserId());
        checkNickname(requestDto.getNickname());
    }

    private void checkUserId(String userId) {
        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isPresent()) {
            throw new UserIdAlreadyExistException("UserId Already Exist");
        }
    }

    private void checkNickname(String nickname) {
        Optional<User> findUser = userRepository.findByNickname(nickname);
        if (findUser.isPresent()) {
            throw new UserIdAlreadyExistException("Nickname Already Exist");
        }
    }
}
