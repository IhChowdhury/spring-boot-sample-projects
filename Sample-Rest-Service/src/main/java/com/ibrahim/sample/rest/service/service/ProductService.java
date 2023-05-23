package com.ibrahim.sample.rest.service.service;

import com.ibrahim.sample.rest.service.exception.ResourceNotFoundException;
import com.ibrahim.sample.rest.service.model.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private List<Product> productList = new ArrayList<>();

    public List<Product> getAllProducts(){
        return productList;
    }

    public Product addNewProduct(String name, String category, Double price){
        Product newProduct = new Product(name, category, price);
        productList.add(newProduct);
        return newProduct;
    }

    public Product getProductById(UUID productId) throws ResourceNotFoundException {
        Optional<Product> searchedProduct = productList.stream().filter(product -> product.getId().equals(productId)).findFirst();
        if(searchedProduct.isPresent()){
            return searchedProduct.get();
        }
        throw new ResourceNotFoundException(String.format("Product not found using product Id %s", productId));
    }
}
