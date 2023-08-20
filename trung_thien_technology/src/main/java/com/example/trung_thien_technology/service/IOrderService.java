package com.example.trung_thien_technology.service;

import com.example.trung_thien_technology.dto.OrderDetailDTO;
import com.example.trung_thien_technology.model.Customers;

import java.util.List;

public interface IOrderService {

    void saveOrder(List<OrderDetailDTO> shoppingCartDTOS, Customers customers);

}
