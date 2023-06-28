package com.dongne.dongnebe.global.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;

@Getter
@SuperBuilder
public class ErrorResponseDto extends ResponseDto {
    public ErrorResponseDto(int statusCode, String responseMessage) {
        super(statusCode, responseMessage);
    }
    public static ErrorResponseDto badRequest(String message){
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), message);
    }
    public static ErrorResponseDto forbidden(String message) {
        return new ErrorResponseDto(HttpStatus.FORBIDDEN.value(), message);
    }
    public static ErrorResponseDto notFound(String message) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), message);
    }
    public static ErrorResponseDto unauthorized(String message) {
        return new ErrorResponseDto(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
