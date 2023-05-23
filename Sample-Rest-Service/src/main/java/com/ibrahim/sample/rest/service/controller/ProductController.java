package com.ibrahim.sample.rest.service.controller;

import com.ibrahim.sample.rest.service.exception.ResourceNotFoundException;
import com.ibrahim.sample.rest.service.model.entity.Product;
import com.ibrahim.sample.rest.service.model.request.NewProductRequest;
import com.ibrahim.sample.rest.service.model.response.ApiResponse;
import com.ibrahim.sample.rest.service.model.response.ErrorResponse;
import com.ibrahim.sample.rest.service.model.response.SuccessResponse;
import com.ibrahim.sample.rest.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse> addNewProduct(@RequestBody NewProductRequest product){
        Product newProduct = productService.addNewProduct(product.getProductName(), product.getProductCategory(),
                product.getProductPrice());
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK);
        successResponse.setResult(newProduct);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts(){
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK);
        successResponse.setResult(productService.getAllProducts());
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable(name = "productId")UUID productId){
        try {
            Product product = productService.getProductById(productId);
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK);
            successResponse.setResult(product);
            return ResponseEntity.ok(successResponse);
        } catch (ResourceNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST);
            errorResponse.setErrorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
