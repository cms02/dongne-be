package com.dongne.dongnebe.domain.user.controller;

import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitUserService initUserService;

    @PostConstruct
    public void init() {
        initUserService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitUserService {

        private final UserRepository memberRepository;
        private final PasswordEncoder passwordEncoder;

        @Transactional
        public void init() {
            String encPwd1 = passwordEncoder.encode("password1");
            String encPwd2 = passwordEncoder.encode("password2");

            User user1 = User.builder()
                    .userId("userId1")
                    .address("서울시 성동구")
                    .password(encPwd1)
                    .nickname("성동구 깡패 김소연")
                    .role(Role.USER)
                    .build();
            User user2 = User.builder()
                    .userId("userId2")
                    .address("서울시 광진구")
                    .password(encPwd2)
                    .nickname("광진구 찐따 김소연")
                    .role(Role.USER)
                    .build();

            memberRepository.save(user1);
            memberRepository.save(user2);
        }
    }
}
