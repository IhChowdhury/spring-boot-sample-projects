package com.ibrahim.sample.rest.service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
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
