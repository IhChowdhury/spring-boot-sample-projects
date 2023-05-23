package com.ibrahim.sample.rest.service.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorResponse extends ApiResponse {

    private String errorMessage;

    public ErrorResponse(HttpStatus statusCode) {
        super(statusCode);
    }
}
