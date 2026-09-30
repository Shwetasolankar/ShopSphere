package com.shopsphere.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shopsphere.entity.Product;
import com.shopsphere.exception.ProductNotFoundException;
import com.shopsphere.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ==============================
    // GET ALL PRODUCTS
    // ==============================

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ==============================
    // PAGINATION
    // ==============================

    public Page<Product> getProductsWithPagination(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // ==============================
    // GET PRODUCT BY ID
    // ==============================

    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));
    }

    // ==============================
    // SEARCH BY NAME
    // ==============================

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // ==============================
    // SEARCH BY CATEGORY
    // ==============================

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // ==============================
    // ADD PRODUCT
    // ==============================

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // ==============================
    // SORT LOW TO HIGH
    // ==============================

    public List<Product> getProductsByPriceLowToHigh() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    // ==============================
    // SORT HIGH TO LOW
    // ==============================

    public List<Product> getProductsByPriceHighToLow() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    // ==============================
    // UPDATE PRODUCT
    // ==============================

    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    // ==============================
    // DELETE PRODUCT
    // ==============================

    public void deleteProduct(Long id) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        productRepository.delete(existingProduct);
    }

    // ==============================
    // CATEGORY + PAGINATION
    // ==============================

    public Page<Product> getProductsByCategoryWithPagination(
            String category,
            Pageable pageable) {

        return productRepository.findByCategoryIgnoreCase(
                category,
                pageable);
    }

    // ==============================
    // SEARCH + CATEGORY + PAGINATION
    // ==============================

    public Page<Product> searchByNameAndCategory(
            String name,
            String category,
            Pageable pageable) {

        return productRepository
                .findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
                        name,
                        category,
                        pageable);
    }

    // ==============================
    // CHECK STOCK STATUS
    // ==============================

    public String getStockStatus(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        if (product.getQuantity() > 0) {

            return "Product " + product.getName()
                    + " is IN STOCK. Quantity: "
                    + product.getQuantity();
        }

        return "Product " + product.getName()
                + " is OUT OF STOCK.";
    }

    // ==============================
    // LOW STOCK PRODUCTS
    // ==============================

    public List<Product> getLowStockProducts(int limit) {
        return productRepository.findByQuantityLessThanEqual(limit);
    }

    // ==============================
    // OUT OF STOCK PRODUCTS
    // ==============================

    public List<Product> getOutOfStockProducts() {
        return productRepository.findByQuantity(0);
    }

    // ==============================
    // TOTAL PRODUCT COUNT
    // ==============================

    public long getProductCount() {
        return productRepository.count();
    }

    // ==============================
    // CATEGORY PRODUCT COUNT
    // ==============================

    public long getCategoryProductCount(String category) {
        return productRepository.countByCategoryIgnoreCase(category);
    }

    // ==============================
    // TOTAL STOCK QUANTITY
    // ==============================

    public int getTotalStockQuantity() {

        return productRepository.findAll()
                .stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    // ==============================
    // HIGHEST PRICE PRODUCT
    // ==============================

    public Product getHighestPriceProduct() {
        return productRepository.findTopByOrderByPriceDesc();
    }

    // ==============================
    // LOWEST PRICE PRODUCT
    // ==============================

    public Product getLowestPriceProduct() {
        return productRepository.findTopByOrderByPriceAsc();
    }

    // ==============================
    // SELL PRODUCT / REDUCE STOCK
    // ==============================

    public Product sellProduct(Long id, int quantity) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        if (quantity <= 0) {
            throw new RuntimeException(
                    "Sell quantity must be greater than 0");
        }

        if (product.getQuantity() < quantity) {
            throw new RuntimeException(
                    "Insufficient stock. Available quantity: "
                            + product.getQuantity());
        }

        product.setQuantity(
                product.getQuantity() - quantity);

        return productRepository.save(product);
    }

    // ==============================
    // PRICE RANGE
    // ==============================

    public List<Product> getProductsByPriceRange(
            double minPrice,
            double maxPrice) {

        return productRepository.findByPriceBetween(
                minPrice,
                maxPrice);
    }
}