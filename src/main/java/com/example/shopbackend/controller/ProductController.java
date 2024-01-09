package com.example.shopbackend.controller;

import com.example.shopbackend.dto.ProductDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @PostMapping()
    public void addProduct(@RequestBody ProductDto productDto) {

    }
}
