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
public class SignUpRequestDto {
    @Size(max = 10, message = "10 글자 이내의 ID를 입력하세요.")
    private String userId;

    @Size(max = 5, message = "5 글자 이내의 이름를 입력하세요.")
    private String username;

    @Size(max = 15, message = "15 글자 이내의 ID를 입력하세요.")
    private String password;

    @Size(max = 10, message = "7 글자 이내의 ID를 입력하세요.")
    private String nickname;

    private String address;
}
