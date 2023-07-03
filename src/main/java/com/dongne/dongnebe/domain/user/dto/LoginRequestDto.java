package com.dongne.dongnebe.domain.user.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @Size(max = 10, message = "10 글자 이내의 ID를 입력하세요.")
    private String userId;
    @Size(max = 15, message = "15 글자 이내의 비밀번호를 입력하세요.")
    private String password;
}
