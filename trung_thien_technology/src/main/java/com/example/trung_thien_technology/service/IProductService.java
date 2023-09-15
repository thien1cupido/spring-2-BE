package com.example.trung_thien_technology.service;


import com.example.trung_thien_technology.dto.OrderDetailDTO;

import com.example.trung_thien_technology.model.Products;
import com.example.trung_thien_technology.projection.IProductProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<IProductProjection> findAll(Pageable pageable, Integer type);

    List<IProductProjection> findAllByType(Integer type);

    List<Products> findAllByCustomer();
    Page<IProductProjection> findAllProduct(Pageable pageable, String nameSearch);
    Page<IProductProjection> findAllProductAdmin(Pageable pageable);
    Optional<IProductProjection> findProductById(Integer productId);

    boolean deleteProductById(Integer id);
    boolean checkQuantity(List<OrderDetailDTO> orderDetailDTOS);
}
