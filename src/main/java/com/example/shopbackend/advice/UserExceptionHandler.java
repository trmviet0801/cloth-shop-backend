package com.example.shopbackend.advice;

import com.example.shopbackend.exception.DuplicatedUser;
import com.example.shopbackend.exception.NotContainRequiredData;
import com.example.shopbackend.model.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handlerUserException(NotContainRequiredData exception) {
        UserErrorResponse err = new UserErrorResponse();
        err.setMessage(exception.getMessage());
        err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        err.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> duplicatedUserExceptionHandler(DuplicatedUser exception) {
        UserErrorResponse exc = new UserErrorResponse();
        exc.setMessage(exception.getMessage());
        exc.setStatus(HttpStatus.CONFLICT);
        exc.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(exc, HttpStatus.CONFLICT);
    }
}
