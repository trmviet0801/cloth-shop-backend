package com.example.shopbackend.service.imp;

import com.example.shopbackend.model.Cart;
import com.example.shopbackend.repository.CartRepository;
import com.example.shopbackend.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImp(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
