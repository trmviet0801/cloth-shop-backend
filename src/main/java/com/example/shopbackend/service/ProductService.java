package com.example.shopbackend.service;

import com.example.shopbackend.exception.ProductNotFound;
import com.example.shopbackend.model.Product;

public interface ProductService {
    boolean isProductAvailable(Product product);
    Product getProduct(long productId) throws ProductNotFound;
    long saveProduct(Product product);
}
