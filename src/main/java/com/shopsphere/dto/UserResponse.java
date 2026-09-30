package com.shopsphere.dto;

public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private String token;

    // ==============================
    // CONSTRUCTOR
    // ==============================

    public UserResponse(
            Long id,
            String name,
            String email,
            String token) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    // ==============================
    // GET ID
    // ==============================

    public Long getId() {
        return id;
    }

    // ==============================
    // GET NAME
    // ==============================

    public String getName() {
        return name;
    }

    // ==============================
    // GET EMAIL
    // ==============================

    public String getEmail() {
        return email;
    }

    // ==============================
    // GET TOKEN
    // ==============================

    public String getToken() {
        return token;
    }
}