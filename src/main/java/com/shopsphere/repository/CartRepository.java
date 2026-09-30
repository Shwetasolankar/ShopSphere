package com.shopsphere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopsphere.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByProductId(Long productId);

    boolean existsByProductId(Long productId);
}