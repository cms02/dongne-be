package com.dongne.dongnebe.domain.user.service;

import com.dongne.dongnebe.domain.user.dto.*;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.user.jwt.JwtTokenProvider;
import com.dongne.dongnebe.domain.user.redis.RedisService;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @Value("${file.path}")
    private String uploadFolder;

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
    public ResponseDto updateUsersBasic(String userId, MultipartFile file, BasicRequestDto requestDto, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);

        /*파일이 없다면 기본 프로필 적용*/


        if (requestDto.getIsProfileChanged()) {
            uploadFile(file);
            user.updateProfileImg(getImgFilePath(file));
        }
        user.updateBasic(requestDto);
        return ResponseDto.builder()
                .responseMessage("User Update Success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private void uploadFile(MultipartFile file) {
        String imgFilePath = getImgFilePath(file);
        Path path = Paths.get(imgFilePath);
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new ProfileUploadException(e.getMessage());
        }
    }

    private String getImgFilePath(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String imgFileName = uuid + "_" + file.getOriginalFilename();
        String imgFilePath = uploadFolder + imgFileName;
        return imgFilePath;
    }

    private User findUser(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(
                () -> new UserIdNotFoundException("User Id Not Found")
        );
    }

    private static void validatePermission(String userId, Authentication authentication) {
        if (!authentication.getName().equals(userId)) {
            throw new ForbiddenException("Access Is Forbidden");
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

    @Transactional
    public ResponseDto updateUsersPassword(String userId, PasswordRequestDto passwordRequestDto, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);
        user.updatePassword(passwordEncoder.encode(passwordRequestDto.getPassword()));
        return ResponseDto.builder()
                .responseMessage("User Password Updated")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto confirmUsersPassword(String userId, PasswordRequestDto passwordRequestDto, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);
        boolean isPasswordMatch = isPasswordMatch(passwordRequestDto.getPassword(), user.getPassword());
        if (!isPasswordMatch) {
            throw new IncorrectPasswordException("Incorrect Password");
        }
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Correct Password")
                .build();
    }

    private boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public UsersBasicResponseDto findUsersBasic(String userId, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);
        return UsersBasicResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Find Users Basic")
                .profile_img(user.getProfileImg())
                .address(user.getAddress())
                .nickname(user.getNickname())
                .build();
    }

}
