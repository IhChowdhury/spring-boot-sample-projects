package com.ibrahim.sample.rest.service.repositories;

import com.ibrahim.sample.rest.service.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepositoy extends JpaRepository<Product, UUID> {
}
