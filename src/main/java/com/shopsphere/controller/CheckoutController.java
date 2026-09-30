package com.shopsphere.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopsphere.entity.Order;
import com.shopsphere.service.CheckoutService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // ==============================
    // CHECKOUT CART
    // ==============================

    @PostMapping
    public List<Order> checkout() {

        return checkoutService.checkout();
    }
}