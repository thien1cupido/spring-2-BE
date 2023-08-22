package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.dto.OrderDetailDTO;
import com.example.trung_thien_technology.model.Products;
import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.projection.IShoppingCartProjection;
import com.example.trung_thien_technology.repository.IProductRepository;
import com.example.trung_thien_technology.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;


    @Override
    public Page<IProductProjection> findAll(Pageable pageable, Integer type) {
        return iProductRepository.findAllByIsAndDelete(pageable, type);
    }

    @Override
    public List<IProductProjection> findAllByType(Integer type) {
        return iProductRepository.findAllByPC(type);
    }

    @Override
    public List<Products> findAllByCustomer() {
        return iProductRepository.findAllByCustomer();
    }

    @Override
    public Page<IProductProjection> findAllProduct(Pageable pageable, String nameSearch) {
        return iProductRepository.findAllProduct(pageable, nameSearch);
    }

    @Override
    public Page<IShoppingCartProjection> findAllProductAdmin(Pageable pageable, String nameSearch) {
        return iProductRepository.findAllProductAdmin(pageable, nameSearch);
    }

    @Override
    public Optional<IProductProjection> findProductById(Integer productId) {
        return iProductRepository.findProductById(productId);
    }

    @Override
    public boolean deleteProductById(Integer id) {
        try {
            iProductRepository.deleteProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean checkQuantity(List<OrderDetailDTO> orderDetailDTOS) {
        List<Integer> list = this.iProductRepository.findAllByCustomer().stream().map(Products::getId).collect(Collectors.toList());
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            if (list.contains(orderDetailDTO.getId())) {
                return true;
            }
        }
        return false;
    }
}