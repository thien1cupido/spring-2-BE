package com.example.trung_thien_technology.projection;


public interface IShoppingCartProjection {
    Integer getId();

    String getName();

    Long getPrice();

    Integer getQuantity();

    Integer getTotalQuantity();

    String getImage();
}