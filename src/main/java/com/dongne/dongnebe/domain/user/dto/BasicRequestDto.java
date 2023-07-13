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
public class BasicRequestDto {

    @Size(max = 10, message = "7 글자 이내의 닉네임을 입력하세요.")
    private String nickname;
    private String cityCode;
    private String zoneCode;
    private Boolean isProfileChanged;

}
