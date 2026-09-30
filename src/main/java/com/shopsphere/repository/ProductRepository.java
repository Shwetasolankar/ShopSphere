package com.shopsphere.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopsphere.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // ==============================
    // SEARCH BY NAME
    // ==============================

    List<Product> findByNameContainingIgnoreCase(String name);


    // ==============================
    // SEARCH BY CATEGORY
    // ==============================

    List<Product> findByCategoryIgnoreCase(String category);


    // ==============================
    // SORT PRICE LOW TO HIGH
    // ==============================

    List<Product> findAllByOrderByPriceAsc();


    // ==============================
    // SORT PRICE HIGH TO LOW
    // ==============================

    List<Product> findAllByOrderByPriceDesc();


    // ==============================
    // CATEGORY + PAGINATION
    // ==============================

    Page<Product> findByCategoryIgnoreCase(
            String category,
            Pageable pageable);


    // ==============================
    // SEARCH + CATEGORY + PAGINATION
    // ==============================

    Page<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
            String name,
            String category,
            Pageable pageable);


    // ==============================
    // LOW STOCK
    // ==============================

    List<Product> findByQuantityLessThanEqual(int quantity);


    // ==============================
    // OUT OF STOCK
    // ==============================

    List<Product> findByQuantity(int quantity);


    // ==============================
    // CATEGORY COUNT
    // ==============================

    long countByCategoryIgnoreCase(String category);


    // ==============================
    // HIGHEST PRICE PRODUCT
    // ==============================

    Product findTopByOrderByPriceDesc();


    // ==============================
    // LOWEST PRICE PRODUCT
    // ==============================

    Product findTopByOrderByPriceAsc();


    // ==============================
    // PRICE RANGE
    // ==============================

    List<Product> findByPriceBetween(
            double minPrice,
            double maxPrice);

}