package com.shopsphere.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopsphere.entity.Cart;
import com.shopsphere.entity.Order;
import com.shopsphere.entity.Product;
import com.shopsphere.repository.CartRepository;
import com.shopsphere.repository.OrderRepository;
import com.shopsphere.repository.ProductRepository;

@Service
public class CheckoutService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public CheckoutService(
            CartRepository cartRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    // ==============================
    // CHECKOUT CART
    // ==============================

    @Transactional
    public List<Order> checkout() {

        List<Cart> cartItems = cartRepository.findAll();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        List<Order> orders = new ArrayList<>();

        for (Cart cart : cartItems) {

            Product product = productRepository
                    .findById(cart.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found with id: "
                                            + cart.getProductId()));

            if (cart.getQuantity() <= 0) {
                throw new RuntimeException(
                        "Invalid cart quantity for product: "
                                + cart.getProductId());
            }

            if (product.getQuantity() < cart.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: "
                                + product.getName()
                                + ". Available quantity: "
                                + product.getQuantity());
            }

            double totalPrice =
                    product.getPrice() * cart.getQuantity();

            Order order = new Order();

            order.setProductId(product.getId());
            order.setQuantity(cart.getQuantity());
            order.setTotalPrice(totalPrice);

            product.setQuantity(
                    product.getQuantity()
                            - cart.getQuantity());

            productRepository.save(product);

            Order savedOrder = orderRepository.save(order);

            orders.add(savedOrder);
        }

        // Clear cart after successful checkout
        cartRepository.deleteAll();

        return orders;
    }
}