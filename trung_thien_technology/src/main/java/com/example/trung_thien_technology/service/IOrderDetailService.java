package com.example.trung_thien_technology.service;


import com.example.trung_thien_technology.projection.IOrderDetailProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderDetailService {

    Page<IOrderDetailProjection> findAllOrderDetail(Pageable pageable, Integer orderId);

    List<IOrderDetailProjection> findAllOrderDetail(Integer orderId);
}
