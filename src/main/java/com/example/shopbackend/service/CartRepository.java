package com.example.shopbackend.service;

import com.example.shopbackend.dto.CartDto;
import com.example.shopbackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
