package com.example.trung_thien_technology.projection;

public interface IOrderProjection {
    Integer getId();

    Integer getOrderCode();

    String getCreateTime();

    String getPaymentType();

    String getTotalPrice();

    Boolean getPaymentStatus();
}
