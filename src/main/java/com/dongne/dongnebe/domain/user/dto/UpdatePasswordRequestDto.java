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
public class UpdatePasswordRequestDto {
    @Size(max = 15, message = "15 글자 이내의 비밀번호를 입력하세요.")
    private String password;
}
