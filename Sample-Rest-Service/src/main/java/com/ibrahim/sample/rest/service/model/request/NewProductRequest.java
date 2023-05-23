package com.ibrahim.sample.rest.service.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewProductRequest {
    private String productName;
    private String productCategory;
    private Double productPrice;

}
