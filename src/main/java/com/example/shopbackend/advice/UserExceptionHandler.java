package com.example.shopbackend.advice;

import com.example.shopbackend.exception.*;
import com.example.shopbackend.model.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> duplicatedUserExceptionHandler(DuplicatedUser exception) {
        UserErrorResponse exc = new UserErrorResponse();
        exc.setMessage(exception.getMessage());
        exc.setStatus(HttpStatus.CONFLICT);
        exc.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(exc, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> usernameNotFoundExceptionHandler(UsernameNotFoundException exception) {
        UserErrorResponse exc = new UserErrorResponse();
        exc.setMessage("Username not found");
        exc.setStatus(HttpStatus.UNAUTHORIZED);
        exc.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(exc, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> userNotFoundExceptionHandler(UserNotFound exception) {
        UserErrorResponse exc = new UserErrorResponse();
        exc.setTimestamp(System.currentTimeMillis());
        exc.setStatus(HttpStatus.NOT_FOUND);
        exc.setMessage(exception.getMessage());
        return new ResponseEntity<>(exc, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> invalidUserExceptionHandler(InvalidUser exception) {
        UserErrorResponse exc = new UserErrorResponse();
        exc.setTimestamp(System.currentTimeMillis());
        exc.setStatus(HttpStatus.UNAUTHORIZED);
        exc.setMessage(exception.getMessage());
        return new ResponseEntity<>(exc, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> userExceptionHandler(NotContainRequiredData exception) {
        UserErrorResponse err = new UserErrorResponse();
        err.setMessage(exception.getMessage());
        err.setStatus(HttpStatus.BAD_REQUEST);
        err.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
