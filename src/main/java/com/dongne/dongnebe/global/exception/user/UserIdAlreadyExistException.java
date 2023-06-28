package com.dongne.dongnebe.global.exception.user;

public class UserIdAlreadyExistException extends RuntimeException{
    public UserIdAlreadyExistException(String message) {
        super(message);
    }
}
