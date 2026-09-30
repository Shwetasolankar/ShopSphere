package com.shopsphere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    // ==============================
    // DEFAULT CONSTRUCTOR
    // ==============================

    public User() {
    }

    // ==============================
    // GET ID
    // ==============================

    public Long getId() {
        return id;
    }

    // ==============================
    // SET ID
    // ==============================

    public void setId(Long id) {
        this.id = id;
    }

    // ==============================
    // GET NAME
    // ==============================

    public String getName() {
        return name;
    }

    // ==============================
    // SET NAME
    // ==============================

    public void setName(String name) {
        this.name = name;
    }

    // ==============================
    // GET EMAIL
    // ==============================

    public String getEmail() {
        return email;
    }

    // ==============================
    // SET EMAIL
    // ==============================

    public void setEmail(String email) {
        this.email = email;
    }

    // ==============================
    // GET PASSWORD
    // ==============================

    public String getPassword() {
        return password;
    }

    // ==============================
    // SET PASSWORD
    // ==============================

    public void setPassword(String password) {
        this.password = password;
    }
}