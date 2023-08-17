package com.example.trung_thien_technology.projection;

public interface ICartProjection {
    Integer getId();
    Integer getCartDetailId();
    String getProductName();
    String getDescription();
    Integer getPrice();
    Integer getQuantity();
    Integer getProductId();
    Integer getInventoryLevel();
}
