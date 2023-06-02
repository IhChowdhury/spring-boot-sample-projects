package com.ibrahim.sample.rest.service.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")
    private String email;
    @NotNull
    @NotBlank
    private String password;

}
