package com.shopsphere.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopsphere.entity.Order;
import com.shopsphere.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ==============================
    // GET ALL ORDERS
    // ==============================

    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }

    // ==============================
    // GET ORDER BY ID
    // ==============================

    @GetMapping("/{id}")
    public Order getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);
    }

    // ==============================
    // CREATE ORDER
    // ==============================

    @PostMapping
    public Order createOrder(
            @RequestBody Order order) {

        return orderService.createOrder(order);
    }

    // ==============================
    // GET ORDERS BY PRODUCT ID
    // ==============================

    @GetMapping("/product/{productId}")
    public List<Order> getOrdersByProductId(
            @PathVariable Long productId) {

        return orderService.getOrdersByProductId(productId);
    }

    // ==============================
    // TOTAL ORDER COUNT
    // ==============================

    @GetMapping("/count")
    public long getOrderCount() {

        return orderService.getOrderCount();
    }

    // ==============================
    // PRODUCT ORDER COUNT
    // ==============================

    @GetMapping("/product/{productId}/count")
    public long getProductOrderCount(
            @PathVariable Long productId) {

        return orderService.getProductOrderCount(productId);
    }

    // ==============================
    // TOTAL REVENUE
    // ==============================

    @GetMapping("/revenue")
    public double getTotalRevenue() {

        return orderService.getTotalRevenue();
    }

    // ==============================
    // DELETE ORDER
    // ==============================

    @DeleteMapping("/{id}")
    public String deleteOrder(
            @PathVariable Long id) {

        orderService.deleteOrder(id);

        return "Order deleted successfully";
    }

}