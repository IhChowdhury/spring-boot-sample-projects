package com.ibrahim.sample.rest.service.controller;

import com.ibrahim.sample.rest.service.model.entity.User;
import com.ibrahim.sample.rest.service.model.request.UserRequest;
import com.ibrahim.sample.rest.service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
@Tag(name = "Authentication", description = "User Authentication Management")
public class AuthenticationController {

    private final UserService userService;

    @Operation(summary = "Create new user API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Successfully created.",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Some validation Error happened!")
    })
    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUpUser(@Valid @RequestBody UserRequest userRequest) {
        var newUser = userService.addNewUser(userRequest);
        return ResponseEntity.ok(newUser);
    }
}
