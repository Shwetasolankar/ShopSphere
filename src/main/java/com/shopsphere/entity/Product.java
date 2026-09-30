package com.shopsphere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Product name is required")
    private String name;


    @Min(value = 0, message = "Price cannot be negative")
    private double price;


    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;


    @NotBlank(message = "Category is required")
    private String category;


    // Default constructor required by JPA
    public Product() {

    }


    // GET ID
    public Long getId() {

        return id;
    }


    // SET ID
    public void setId(Long id) {

        this.id = id;
    }


    // GET NAME
    public String getName() {

        return name;
    }


    // SET NAME
    public void setName(String name) {

        this.name = name;
    }


    // GET PRICE
    public double getPrice() {

        return price;
    }


    // SET PRICE
    public void setPrice(double price) {

        this.price = price;
    }


    // GET QUANTITY
    public int getQuantity() {

        return quantity;
    }


    // SET QUANTITY
    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }


    // GET CATEGORY
    public String getCategory() {

        return category;
    }


    // SET CATEGORY
    public void setCategory(String category) {

        this.category = category;
    }

}