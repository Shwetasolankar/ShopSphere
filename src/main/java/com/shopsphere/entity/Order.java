package com.shopsphere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int quantity;

    private double totalPrice;

    // ==============================
    // DEFAULT CONSTRUCTOR
    // ==============================

    public Order() {
    }

    // ==============================
    // GET ID
    // ==============================

    public Long getId() {
        return id;
    }

    // ==============================
    // SET ID
    // ==============================

    public void setId(Long id) {
        this.id = id;
    }

    // ==============================
    // GET PRODUCT ID
    // ==============================

    public Long getProductId() {
        return productId;
    }

    // ==============================
    // SET PRODUCT ID
    // ==============================

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // ==============================
    // GET QUANTITY
    // ==============================

    public int getQuantity() {
        return quantity;
    }

    // ==============================
    // SET QUANTITY
    // ==============================

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ==============================
    // GET TOTAL PRICE
    // ==============================

    public double getTotalPrice() {
        return totalPrice;
    }

    // ==============================
    // SET TOTAL PRICE
    // ==============================

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}