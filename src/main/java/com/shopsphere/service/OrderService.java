package com.shopsphere.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopsphere.entity.Order;
import com.shopsphere.entity.Product;
import com.shopsphere.repository.OrderRepository;
import com.shopsphere.repository.ProductRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(
            OrderRepository orderRepository,
            ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // ==============================
    // GET ALL ORDERS
    // ==============================

    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    // ==============================
    // GET ORDER BY ID
    // ==============================

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found with id: " + id));
    }

    // ==============================
    // CREATE ORDER + REDUCE STOCK
    // ==============================

    @Transactional
    public Order createOrder(Order order) {

        if (order.getProductId() == null) {

            throw new RuntimeException(
                    "Product ID is required");
        }

        if (order.getQuantity() <= 0) {

            throw new RuntimeException(
                    "Order quantity must be greater than 0");
        }

        Product product = productRepository
                .findById(order.getProductId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: "
                                        + order.getProductId()));

        if (product.getQuantity() < order.getQuantity()) {

            throw new RuntimeException(
                    "Insufficient stock. Available quantity: "
                            + product.getQuantity());
        }

        double totalPrice =
                product.getPrice() * order.getQuantity();

        order.setTotalPrice(totalPrice);

        product.setQuantity(
                product.getQuantity()
                        - order.getQuantity());

        productRepository.save(product);

        return orderRepository.save(order);
    }

    // ==============================
    // GET ORDERS BY PRODUCT ID
    // ==============================

    public List<Order> getOrdersByProductId(Long productId) {

        return orderRepository.findByProductId(productId);
    }

    // ==============================
    // TOTAL ORDER COUNT
    // ==============================

    public long getOrderCount() {

        return orderRepository.count();
    }

    // ==============================
    // PRODUCT ORDER COUNT
    // ==============================

    public long getProductOrderCount(Long productId) {

        return orderRepository.countByProductId(productId);
    }

    // ==============================
    // CANCEL ORDER + RESTORE STOCK
    // ==============================

    @Transactional
    public void deleteOrder(Long id) {

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found with id: " + id));

        Product product = productRepository
                .findById(existingOrder.getProductId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: "
                                        + existingOrder.getProductId()));

        // Restore product stock
        product.setQuantity(
                product.getQuantity()
                        + existingOrder.getQuantity());

        productRepository.save(product);

        // Delete order
        orderRepository.delete(existingOrder);
    }

    // ==============================
    // TOTAL REVENUE
    // ==============================

    public double getTotalRevenue() {

        return orderRepository.getTotalRevenue();
    }
}