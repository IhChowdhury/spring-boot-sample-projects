package com.ibrahim.sample.rest.service.advices;

import com.ibrahim.sample.rest.service.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AthenticationExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> userAlreadyExist(UserAlreadyExistException exception){
        Map<String, String> result = new HashMap<>();
        result.put("error", exception.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
