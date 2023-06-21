package com.dongne.dongnebe.global.handler;

import com.dongne.dongnebe.global.dto.ErrorResponseDto;
import com.dongne.dongnebe.global.exception.user.UserIdAlreadyExistException;
import com.dongne.dongnebe.global.exception.user.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDto> userIdNotFoundExceptionHandler(UserIdNotFoundException exception) {
        return new ResponseEntity<>(ErrorResponseDto.notFound(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponseDto> userIdAlreadyExistHandler(UserIdAlreadyExistException exception) {
        return new ResponseEntity<>(ErrorResponseDto.badRequest(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
