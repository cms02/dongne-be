package com.dongne.dongnebe.domain.user.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class SignUpRequestDto {
    @Size(max = 10, message = "10 글자 이내의 ID를 입력하세요.")
    private String userId;

    @Size(max = 5, message = "5 글자 이내의 이름를 입력하세요.")
    private String username;

    @Size(max = 15, message = "15 글자 이내의 비밀번호를 입력하세요.")
    private String password;

    @Size(max = 10, message = "7 글자 이내의 닉네임를 입력하세요.")
    private String nickname;

    private String cityCode;
    private String zoneCode;
}
