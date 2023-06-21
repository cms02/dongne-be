package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.dto.JoinRequestDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.user.UserIdAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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
