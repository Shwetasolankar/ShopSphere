package com.shopsphere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopsphere.entity.Cart;
import com.shopsphere.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // ==============================
    // GET ALL CART ITEMS
    // ==============================

    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    // ==============================
    // CART ITEM COUNT
    // ==============================

    public long getCartItemCount() {
        return cartRepository.count();
    }

    // ==============================
    // GET CART ITEM BY ID
    // ==============================

    public Cart getCartItemById(Long id) {

        return cartRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart item not found with id: " + id));
    }

    // ==============================
    // GET CART ITEMS BY PRODUCT ID
    // ==============================

    public List<Cart> getCartItemsByProductId(Long productId) {
        return cartRepository.findByProductId(productId);
    }

    // ==============================
    // CHECK PRODUCT IN CART
    // ==============================

    public boolean isProductInCart(Long productId) {
        return cartRepository.existsByProductId(productId);
    }

    // ==============================
    // ADD TO CART
    // ==============================

    public Cart addToCart(Cart cart) {

        if (cart.getQuantity() <= 0) {
            throw new RuntimeException(
                    "Cart quantity must be greater than 0");
        }

        return cartRepository.save(cart);
    }

    // ==============================
    // UPDATE CART QUANTITY
    // ==============================

    public Cart updateCartQuantity(
            Long id,
            int quantity) {

        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart item not found with id: " + id));

        if (quantity <= 0) {
            throw new RuntimeException(
                    "Cart quantity must be greater than 0");
        }

        existingCart.setQuantity(quantity);

        return cartRepository.save(existingCart);
    }

    // ==============================
    // REMOVE CART ITEM
    // ==============================

    public void removeFromCart(Long id) {

        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart item not found with id: " + id));

        cartRepository.delete(existingCart);
    }

    // ==============================
    // REMOVE PRODUCT FROM CART
    // ==============================

    public void removeProductFromCart(Long productId) {

        List<Cart> cartItems =
                cartRepository.findByProductId(productId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException(
                    "Product not found in cart: " + productId);
        }

        cartRepository.deleteAll(cartItems);
    }

    // ==============================
    // CLEAR CART
    // ==============================

    public void clearCart() {
        cartRepository.deleteAll();
    }
}