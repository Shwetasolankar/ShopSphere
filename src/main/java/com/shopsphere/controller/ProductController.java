package com.shopsphere.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsphere.entity.Product;
import com.shopsphere.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ==============================
    // GET ALL PRODUCTS
    // ==============================

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // ==============================
    // TOTAL PRODUCT COUNT
    // ==============================

    @GetMapping("/count")
    public long getProductCount() {
        return productService.getProductCount();
    }

    // ==============================
    // TOTAL STOCK
    // ==============================

    @GetMapping("/stock/total")
    public int getTotalStockQuantity() {
        return productService.getTotalStockQuantity();
    }

    // ==============================
    // HIGHEST PRICE
    // ==============================

    @GetMapping("/highest-price")
    public Product getHighestPriceProduct() {
        return productService.getHighestPriceProduct();
    }

    // ==============================
    // LOWEST PRICE
    // ==============================

    @GetMapping("/lowest-price")
    public Product getLowestPriceProduct() {
        return productService.getLowestPriceProduct();
    }

    // ==============================
    // CATEGORY COUNT
    // ==============================

    @GetMapping("/category/{category}/count")
    public long getCategoryProductCount(
            @PathVariable String category) {

        return productService.getCategoryProductCount(category);
    }

    // ==============================
    // SEARCH BY NAME
    // ==============================

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String name) {

        return productService.searchProductsByName(name);
    }

    // ==============================
    // SEARCH BY CATEGORY
    // ==============================

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(
            @PathVariable String category) {

        return productService.getProductsByCategory(category);
    }

    // ==============================
    // SORT PRICE LOW TO HIGH
    // ==============================

    @GetMapping("/sort/price-asc")
    public List<Product> getProductsByPriceLowToHigh() {
        return productService.getProductsByPriceLowToHigh();
    }

    // ==============================
    // SORT PRICE HIGH TO LOW
    // ==============================

    @GetMapping("/sort/price-desc")
    public List<Product> getProductsByPriceHighToLow() {
        return productService.getProductsByPriceHighToLow();
    }

    // ==============================
    // PAGINATION
    // ==============================

    @GetMapping("/page")
    public Page<Product> getProductsWithPagination(
            Pageable pageable) {

        return productService.getProductsWithPagination(pageable);
    }

    // ==============================
    // CATEGORY + PAGINATION
    // ==============================

    @GetMapping("/category/{category}/page")
    public Page<Product> getProductsByCategoryWithPagination(
            @PathVariable String category,
            Pageable pageable) {

        return productService.getProductsByCategoryWithPagination(
                category,
                pageable);
    }

    // ==============================
    // SEARCH + CATEGORY + PAGINATION
    // ==============================

    @GetMapping("/search/category/{category}")
    public Page<Product> searchByNameAndCategory(
            @PathVariable String category,
            @RequestParam String name,
            Pageable pageable) {

        return productService.searchByNameAndCategory(
                name,
                category,
                pageable);
    }

    // ==============================
    // LOW STOCK
    // ==============================

    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts(
            @RequestParam int limit) {

        return productService.getLowStockProducts(limit);
    }

    // ==============================
    // OUT OF STOCK
    // ==============================

    @GetMapping("/out-of-stock")
    public List<Product> getOutOfStockProducts() {
        return productService.getOutOfStockProducts();
    }

    // ==============================
    // PRICE RANGE
    // ==============================

    @GetMapping("/price-range")
    public List<Product> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        return productService.getProductsByPriceRange(
                minPrice,
                maxPrice);
    }

    // ==============================
    // GET PRODUCT BY ID
    // ==============================

    @GetMapping("/{id}")
    public Product getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id);
    }

    // ==============================
    // STOCK STATUS
    // ==============================

    @GetMapping("/{id}/stock")
    public String getStockStatus(
            @PathVariable Long id) {

        return productService.getStockStatus(id);
    }

    // ==============================
    // SELL PRODUCT / REDUCE STOCK
    // ==============================

    @PostMapping("/{id}/sell")
    public Product sellProduct(
            @PathVariable Long id,
            @RequestParam int quantity) {

        return productService.sellProduct(id, quantity);
    }

    // ==============================
    // ADD PRODUCT
    // ==============================

    @PostMapping
    public Product addProduct(
            @Valid @RequestBody Product product) {

        return productService.addProduct(product);
    }

    // ==============================
    // UPDATE PRODUCT
    // ==============================

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    // ==============================
    // DELETE PRODUCT
    // ==============================

    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }
}