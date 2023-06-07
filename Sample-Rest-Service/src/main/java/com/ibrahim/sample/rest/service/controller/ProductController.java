package com.ibrahim.sample.rest.service.controller;

import com.ibrahim.sample.rest.service.exception.ResourceNotFoundException;
import com.ibrahim.sample.rest.service.model.entity.Product;
import com.ibrahim.sample.rest.service.model.request.NewProductRequest;
import com.ibrahim.sample.rest.service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "products")
@Tag(name = "Product", description = "Product management APIs")
@SecurityRequirement(name = "basicScheme")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Add new product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product Successfully created.",
                    headers = {@Header(name = "location", description = "New product URL",
                            schema = @Schema(implementation = URI.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Some validation Error happened!")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody NewProductRequest product) {
        UUID newProductId = productService.addNewProduct(product.getProductName(), product.getProductCategory(),
                product.getProductPrice());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProductId).toUri()).build();
    }

    @Operation(summary = "Get all products")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", content = {@Content(
                    array = @ArraySchema(schema = @Schema(implementation = Product.class)),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            }),
            @ApiResponse(responseCode = "204", description = "No Product found")
    })
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        if (allProducts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allProducts);
    }

    @Operation(summary = "Get a product by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = Product.class), mediaType = MediaType.APPLICATION_JSON_VALUE
                    )}),
            @ApiResponse(responseCode = "404", description = "Product Not found", content = @Content)
    })
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(
            @Parameter(description = "Id of product to be searched") @PathVariable(name = "productId") UUID productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
