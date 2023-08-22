package com.example.trung_thien_technology.service;

import com.example.trung_thien_technology.dto.OrderDetailDTO;
import com.example.trung_thien_technology.model.Customers;
import com.example.trung_thien_technology.projection.IOrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {

    void saveOrder(List<OrderDetailDTO> shoppingCartDTOS, Customers customers);

    void saveOrderByPayPal(List<OrderDetailDTO> orderDetailDTOS, Customers customers);
    Page<IOrderProjection>findAllOrder(Pageable pageable,Integer customerId);




}
