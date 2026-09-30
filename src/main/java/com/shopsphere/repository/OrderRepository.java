package com.shopsphere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopsphere.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ==============================
    // GET ORDERS BY PRODUCT ID
    // ==============================

    List<Order> findByProductId(Long productId);

    // ==============================
    // COUNT ORDERS BY PRODUCT ID
    // ==============================

    long countByProductId(Long productId);

    // ==============================
    // TOTAL REVENUE
    // ==============================

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o")
    double getTotalRevenue();
}