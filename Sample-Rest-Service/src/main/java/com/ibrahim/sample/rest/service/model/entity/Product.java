package com.ibrahim.sample.rest.service.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product {
    private UUID id = UUID.randomUUID();
    private String name;
    private String category;
    private Double price;

    public Product(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
