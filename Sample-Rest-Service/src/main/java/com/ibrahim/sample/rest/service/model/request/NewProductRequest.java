package com.ibrahim.sample.rest.service.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewProductRequest {
    @NotBlank(message = "Product name can not be empty")
    private String productName;
    @NotBlank(message = "Product Category can not be empty")
    private String productCategory;
    @Min(0)
    private Double productPrice;

}
