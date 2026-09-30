package com.shopsphere.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopsphere.dto.UserResponse;
import com.shopsphere.entity.User;
import com.shopsphere.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    // ==============================
    // REGISTER USER
    // ==============================

    @PostMapping("/register")
    public UserResponse registerUser(
            @RequestBody User user) {

        return userService.registerUser(user);
    }

    // ==============================
    // LOGIN USER
    // ==============================

    @PostMapping("/login")
    public UserResponse loginUser(
            @RequestParam String email,
            @RequestParam String password) {

        return userService.loginUser(email, password);
    }

    // ==============================
    // GET ALL USERS
    // ==============================

    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    // ==============================
    // GET USER BY ID
    // ==============================

    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable Long id) {

        return userService.getUserById(id);
    }

    // ==============================
    // DELETE USER
    // ==============================

    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return "User deleted successfully";
    }
}