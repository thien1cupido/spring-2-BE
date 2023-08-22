package com.example.trung_thien_technology.service.impl;


import com.example.trung_thien_technology.projection.IOrderDetailProjection;
import com.example.trung_thien_technology.repository.IOrderDetailRepository;
import com.example.trung_thien_technology.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;


    @Override
    public Page<IOrderDetailProjection> findAllOrderDetail(Pageable pageable, Integer orderId) {
        return this.iOrderDetailRepository.findAllOrderDetail(pageable,orderId);
    }
    @Override
    public List<IOrderDetailProjection> findAllOrderDetail(Integer orderId) {
        return this.iOrderDetailRepository.findAllOrderDetail(orderId);
    }
}
