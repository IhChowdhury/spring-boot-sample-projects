package com.ibrahim.sample.rest.service.controller;

import com.ibrahim.sample.rest.service.exception.UserAlreadyExistException;
import com.ibrahim.sample.rest.service.model.request.UserRequest;
import com.ibrahim.sample.rest.service.model.response.ApiResponse;
import com.ibrahim.sample.rest.service.model.response.ErrorResponse;
import com.ibrahim.sample.rest.service.model.response.SuccessResponse;
import com.ibrahim.sample.rest.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping(value = "/sign-up",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> signUpUser(@RequestBody UserRequest userRequest) {
        try{
            var newUser = userService.addNewUser(userRequest);
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK);
            successResponse.setResult(newUser);
            return ResponseEntity.ok(successResponse);
        } catch (UserAlreadyExistException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST);
            errorResponse.setErrorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
