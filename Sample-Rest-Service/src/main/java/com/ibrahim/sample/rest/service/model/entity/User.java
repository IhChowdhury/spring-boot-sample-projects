package com.ibrahim.sample.rest.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String email;

    @ToString.Exclude
    @JsonIgnore
    private String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
