package com.dongne.dongnebe.global.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ResponseDto {
    private Integer statusCode;
    private String responseMessage;
}
