package com.ibrahim.sample.rest.service.service;

import com.ibrahim.sample.rest.service.exception.ResourceNotFoundException;
import com.ibrahim.sample.rest.service.model.entity.Product;
import com.ibrahim.sample.rest.service.repositories.ProductRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepositoy productRepositoy;

    public List<Product> getAllProducts(){
        return productRepositoy.findAll();
    }

    public UUID addNewProduct(String name, String category, Double price){
        Product newProduct = new Product(name, category, price);
        productRepositoy.save(newProduct);
        return newProduct.getId();
    }

    public Product getProductById(UUID productId) throws ResourceNotFoundException {
        Optional<Product> searchedProduct = productRepositoy.findById(productId);
        if(searchedProduct.isPresent()){
            return searchedProduct.get();
        }
        throw new ResourceNotFoundException(String.format("Product not found using product Id %s", productId));
    }
}
