package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.dto.OrderDetailDTO;
import com.example.trung_thien_technology.model.Customers;
import com.example.trung_thien_technology.model.OrderDetails;
import com.example.trung_thien_technology.model.Orders;
import com.example.trung_thien_technology.model.Products;
import com.example.trung_thien_technology.repository.IOrderDetailRepository;
import com.example.trung_thien_technology.repository.IOrderRepository;
import com.example.trung_thien_technology.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;

    @Autowired
    private IOrderRepository iOrderRepository;

    @Override
    @Transactional
    public void saveOrder(List<OrderDetailDTO> orderDetailDTOS, Customers customers) {
        Orders orders = new Orders(customers);
        this.iOrderRepository.save(orders);
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            this.iOrderDetailRepository.save(new OrderDetails(new Products(orderDetailDTO.getId()), orders, orderDetailDTO.getPrice(), orderDetailDTO.getQuantity()));
        }
    }
}