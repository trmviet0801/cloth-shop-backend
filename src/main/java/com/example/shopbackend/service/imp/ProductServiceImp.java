package com.example.shopbackend.service.imp;

import com.example.shopbackend.exception.ProductNotFound;
import com.example.shopbackend.model.Product;
import com.example.shopbackend.repository.ProductRepository;
import com.example.shopbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isProductAvailable(Product product) {
        Optional<Product> databaseProduct = productRepository.findById(product.getId());
        if (databaseProduct.isPresent() && databaseProduct.get().getQuantity() > 0)
            return true;
        return false;
    }

    @Override
    public Product getProduct(long productId) throws ProductNotFound {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent())
            return product.get();
        throw new ProductNotFound("Product Not Found");
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product) throws ProductNotFound {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        }
        throw new ProductNotFound("Product Not Found");
    }
}
