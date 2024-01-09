package com.example.shopbackend.advice;

import com.example.shopbackend.exception.ProductNotFound;
import com.example.shopbackend.model.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> productNotFoundExceptionHandler(ProductNotFound exception) {
        UserErrorResponse exc = new UserErrorResponse();
        exc.setTimestamp(System.currentTimeMillis());
        exc.setMessage(exception.getMessage());
        exc.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exc, HttpStatus.NOT_FOUND);
    }
}
