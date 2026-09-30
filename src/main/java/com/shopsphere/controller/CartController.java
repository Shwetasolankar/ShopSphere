package com.shopsphere.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsphere.entity.Cart;
import com.shopsphere.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // ==============================
    // GET ALL CART ITEMS
    // ==============================

    @GetMapping
    public List<Cart> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    // ==============================
    // CART ITEM COUNT
    // ==============================

    @GetMapping("/count")
    public long getCartItemCount() {
        return cartService.getCartItemCount();
    }

    // ==============================
    // GET CART ITEM BY ID
    // ==============================

    @GetMapping("/{id}")
    public Cart getCartItemById(
            @PathVariable Long id) {

        return cartService.getCartItemById(id);
    }

    // ==============================
    // GET CART ITEMS BY PRODUCT ID
    // ==============================

    @GetMapping("/product/{productId}")
    public List<Cart> getCartItemsByProductId(
            @PathVariable Long productId) {

        return cartService.getCartItemsByProductId(productId);
    }

    // ==============================
    // CHECK PRODUCT IN CART
    // ==============================

    @GetMapping("/product/{productId}/exists")
    public boolean isProductInCart(
            @PathVariable Long productId) {

        return cartService.isProductInCart(productId);
    }

    // ==============================
    // ADD TO CART
    // ==============================

    @PostMapping
    public Cart addToCart(
            @RequestBody Cart cart) {

        return cartService.addToCart(cart);
    }

    // ==============================
    // UPDATE CART QUANTITY
    // ==============================

    @PutMapping("/{id}")
    public Cart updateCartQuantity(
            @PathVariable Long id,
            @RequestParam int quantity) {

        return cartService.updateCartQuantity(
                id,
                quantity);
    }

    // ==============================
    // REMOVE CART ITEM
    // ==============================

    @DeleteMapping("/{id}")
    public String removeFromCart(
            @PathVariable Long id) {

        cartService.removeFromCart(id);

        return "Cart item removed successfully";
    }

    // ==============================
    // REMOVE PRODUCT FROM CART
    // ==============================

    @DeleteMapping("/product/{productId}")
    public String removeProductFromCart(
            @PathVariable Long productId) {

        cartService.removeProductFromCart(productId);

        return "Product removed from cart successfully";
    }

    // ==============================
    // CLEAR CART
    // ==============================

    @DeleteMapping("/clear")
    public String clearCart() {

        cartService.clearCart();

        return "Cart cleared successfully";
    }
}