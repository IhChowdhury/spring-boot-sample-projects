package com.ibrahim.sample.rest.service.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class SuccessResponse extends ApiResponse {
    private Object result;

    public SuccessResponse(HttpStatus status){
        super(status);
    }
}
