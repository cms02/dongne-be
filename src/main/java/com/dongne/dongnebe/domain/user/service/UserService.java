package com.dongne.dongnebe.domain.user.service;

import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.user.dto.*;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.user.jwt.JwtTokenProvider;
import com.dongne.dongnebe.domain.user.redis.RedisService;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.user.IncorrectPasswordException;
import com.dongne.dongnebe.global.exception.common.ResourceAlreadyExistException;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.dongne.dongnebe.global.service.GlobalService.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public ResponseDto signUpUser(SignUpRequestDto requestDto) {
        validateUser(requestDto);
        userRepository.save(
                User.builder()
                        .userId(requestDto.getUserId())
                        .username(requestDto.getUsername())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .nickname(requestDto.getNickname())
                        .city(City.builder().cityCode(requestDto.getCityCode()).build())
                        .zone(Zone.builder().zoneCode(requestDto.getZoneCode()).build())
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
            throw new ResourceAlreadyExistException("User Id Already Exist");
        }
    }

    private void checkNickname(String nickname) {
        Optional<User> findUser = userRepository.findByNickname(nickname);
        if (findUser.isPresent()) {
            throw new ResourceAlreadyExistException("Nickname Already Exist");
        }
    }

    public LoginResponseDto loginUser(LoginRequestDto requestDto) {
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
    public ResponseDto updateUserBasic(String userId, MultipartFile file, BasicRequestDto requestDto, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);

        /*파일이 없다면 기본 프로필 적용*/

        if (requestDto.getIsProfileChanged()) {
            uploadFile(file);
            user.updateProfileImg(getImgFilePath(file));
        }
        user.updateBasic(requestDto);
        return ResponseDto.builder()
                .responseMessage("User Basic Update Success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }



    private User findUser(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User Id Not Found")
        );
    }

    @Transactional
    public ResponseDto deleteUser(String userId, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);
        user.delete();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("User Deleted")
                .build();
    }

    @Transactional
    public ResponseDto updateUserPassword(String userId, PasswordRequestDto passwordRequestDto, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);
        user.updatePassword(passwordEncoder.encode(passwordRequestDto.getPassword()));
        return ResponseDto.builder()
                .responseMessage("User Password Updated")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseDto confirmUserPassword(String userId, PasswordRequestDto passwordRequestDto, Authentication authentication) {
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

    @Transactional(readOnly = true)
    public UsersBasicResponseDto findUserBasic(String userId, Authentication authentication) {
        validatePermission(userId, authentication);
        User user = findUser(userId);
        return UsersBasicResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Find Users Basic")
                .profileImg(user.getProfileImg())
                .cityName(user.getCity().getName())
                .zoneName(user.getZone().getName())
                .nickname(user.getNickname())
                .build();
    }

}
