package com.example.shopbackend.controller;

import com.example.shopbackend.dto.ProductDto;
import com.example.shopbackend.exception.ProductNotFound;
import com.example.shopbackend.model.Product;
import com.example.shopbackend.service.ProductService;
import com.example.shopbackend.util.Convert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("{productId}")
    public ResponseEntity<ProductDto> editProduct(@RequestBody ProductDto productDto) throws ProductNotFound {
        Product product = productService.editProduct(Convert.dtoToProduct(productDto));
        return  ResponseEntity.ok().body(Convert.productToDto(product));
    }
}
