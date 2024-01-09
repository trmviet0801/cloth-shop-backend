package com.example.shopbackend.controller;

import com.example.shopbackend.dto.ProductDto;
import com.example.shopbackend.model.Product;
import com.example.shopbackend.service.ProductService;
import com.example.shopbackend.util.Convert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
        Product product = productService.addProduct(Convert.dtoToProduct(productDto));
        return ResponseEntity.ok().body(product);
    }
}
