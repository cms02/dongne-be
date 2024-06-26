package com.dongne.dongnebe.domain.user.service;

import com.dongne.dongnebe.domain.board.repository.BoardQueryRepository;
import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.comment.board_comment.repository.BoardCommentQueryRepository;
import com.dongne.dongnebe.domain.user.dto.FindLatestBoardCommentsByUserDto;
import com.dongne.dongnebe.domain.user.dto.FindLatestBoardsByUserDto;
import com.dongne.dongnebe.domain.user.dto.UserRankingDto;
import com.dongne.dongnebe.domain.user.dto.request.*;
import com.dongne.dongnebe.domain.user.dto.response.*;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.user.jwt.JwtTokenProvider;
import com.dongne.dongnebe.global.redis.RedisService;
import com.dongne.dongnebe.domain.user.repository.UserQueryRepository;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.user.IncorrectPasswordException;
import com.dongne.dongnebe.global.exception.common.ResourceAlreadyExistException;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final BoardCommentQueryRepository boardCommentQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserQueryRepository userQueryRepository;

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

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestDto.getUserId(), requestDto.getPassword());
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        /*
        * 위의 인증 과정이 성공하게 되면 스프링 시큐리티에서 내부적으로
        * SecurityContextHolder.getContext().setAuthenticatition(authentication) 을 호출
        * SecurityContext 에 Authentication 객체를 저장한다.
        * */

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
        return new UsersBasicResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UsersMainResponseDto findUserMain(Authentication authentication, Pageable pageable) {
        User user = findUser(authentication.getName());
        List<FindLatestBoardsByUserDto> findLatestBoardsByUserDtos = boardQueryRepository.findLatestBoardsByUser(authentication.getName(), pageable);
        List<FindLatestBoardCommentsByUserDto> findLatestBoardCommentsByUserDtos = boardCommentQueryRepository.findLatestBoardCommentsByUser(authentication.getName(), pageable);

        return new UsersMainResponseDto(user, findLatestBoardsByUserDtos, findLatestBoardCommentsByUserDtos);
    }

    @Transactional
    public ResponseDto logoutUser(Authentication authentication) {
        redisService.deleteValues(authentication.getName());
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Logout Success")
                .build();
    }

    public ReissueResponseDto reissue(Authentication authentication) {
        User user = findUser(authentication.getName());
        String accessToken = jwtTokenProvider.responseAccessToken(user);
        return ReissueResponseDto.builder()
                .accessToken(accessToken)
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Reissue Success")
                .build();
    }

    @Transactional(readOnly = true)
    public UserRankingResponseDto findUserRanking(UserRankingRequestDto userRankingRequestDto, Pageable pageable) {
        List<User> users = userQueryRepository.findUserRanking(userRankingRequestDto, pageable);
        List<UserRankingDto> userRankingDtos = users.stream().map(UserRankingDto::new)
                .collect(Collectors.toList());
        return new UserRankingResponseDto(userRankingDtos);
    }
}
