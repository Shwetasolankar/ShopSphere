package com.shopsphere.service;

import org.springframework.stereotype.Service;

import com.shopsphere.entity.Product;
import com.shopsphere.repository.OrderRepository;
import com.shopsphere.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public DashboardService(
            ProductRepository productRepository,
            OrderRepository orderRepository) {

        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    // ==============================
    // DASHBOARD SUMMARY
    // ==============================

    public Map<String, Object> getDashboardSummary() {

        Map<String, Object> dashboard = new HashMap<>();

        // Total products
        dashboard.put(
                "totalProducts",
                productRepository.count()
        );

        // Total stock quantity
        int totalStock = productRepository.findAll()
                .stream()
                .mapToInt(Product::getQuantity)
                .sum();

        dashboard.put(
                "totalStock",
                totalStock
        );

        // Total orders
        dashboard.put(
                "totalOrders",
                orderRepository.count()
        );

        // Total revenue
        dashboard.put(
                "totalRevenue",
                orderRepository.getTotalRevenue()
        );

        // Highest price product
        dashboard.put(
                "highestPriceProduct",
                productRepository.findTopByOrderByPriceDesc()
        );

        // Lowest price product
        dashboard.put(
                "lowestPriceProduct",
                productRepository.findTopByOrderByPriceAsc()
        );

        return dashboard;
    }
}