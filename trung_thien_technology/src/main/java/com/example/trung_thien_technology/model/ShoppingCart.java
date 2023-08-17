package com.example.trung_thien_technology.model;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "is_delete")
    private boolean isDelete;

    public ShoppingCart() {
    }

    public ShoppingCart(Customers customers, Products products, Integer quantity) {
        this.customers = customers;
        this.products = products;
        this.quantity = quantity;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Products getProducts() {
        return products;
    }

    public Integer getProductsId() {
        return products.getId();
    }
    public void setProducts(Products products) {
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customers getCustomer() {
        return customers;
    }

    public void setCustomer(Customers customers) {
        this.customers = customers;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

}
