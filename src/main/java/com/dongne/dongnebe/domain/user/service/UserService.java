package com.dongne.dongnebe.domain.user.service;

import com.dongne.dongnebe.domain.user.dto.LoginRequestDto;
import com.dongne.dongnebe.domain.user.dto.LoginResponseDto;
import com.dongne.dongnebe.domain.user.dto.SignUpRequestDto;
import com.dongne.dongnebe.domain.user.dto.UpdateRequestDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.user.jwt.JwtTokenProvider;
import com.dongne.dongnebe.domain.user.redis.RedisService;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.user.ForbiddenException;
import com.dongne.dongnebe.global.exception.user.IncorrectPasswordException;
import com.dongne.dongnebe.global.exception.user.UserIdAlreadyExistException;
import com.dongne.dongnebe.global.exception.user.UserIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    public ResponseDto signUpUsers(SignUpRequestDto requestDto) {
        validateUser(requestDto);

        /*ID 중복 처리*/
        userRepository.save(
                User.builder()
                        .userId(requestDto.getUserId())
                        .username(requestDto.getUsername())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .nickname(requestDto.getNickname())
                        .address(requestDto.getAddress())
                        .role(Role.USER)
                        .build());
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("User Sign Up Success")
                .build();
    }

    private void validateUser(SignUpRequestDto requestDto) {
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

    public LoginResponseDto loginUsers(LoginRequestDto requestDto) {
        User user = findUser(requestDto.getUserId());

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect Password");
        }

        String accessToken = jwtTokenProvider.responseAccessToken(user);
        String refreshToken = jwtTokenProvider.responseRefreshToken(user);
        redisService.setValues(user.getUserId(), refreshToken,
                JwtTokenProvider.REFRESH_TOKEN_EXPIRATION_TIME, TimeUnit.MILLISECONDS);

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Login Success")
                .build();
    }

    @Transactional
    public ResponseDto updateUsersBasic(String userId, UpdateRequestDto requestDto, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);
        user.update(requestDto);
        return ResponseDto.builder()
                .responseMessage("User Update Success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private User findUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new UserIdNotFoundException("User Id Not Found")
        );
        return user;
    }

    private static void validatePermission(String userId, Authentication authentication) {
        if (!authentication.getName().equals(userId)) {
            throw new ForbiddenException("Access is forbidden");
        }
    }

    @Transactional
    public ResponseDto deleteUsers(String userId, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);
        user.delete();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("User Deleted")
                .build();
    }
}
