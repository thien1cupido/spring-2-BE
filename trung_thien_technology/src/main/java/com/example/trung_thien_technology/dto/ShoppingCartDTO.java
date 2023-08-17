package com.example.trung_thien_technology.dto;


public class ShoppingCartDTO {
    private Integer id;
    private Integer quantity;

    public ShoppingCartDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
