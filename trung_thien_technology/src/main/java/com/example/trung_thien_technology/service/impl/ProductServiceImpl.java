package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.repository.IProductRepository;
import com.example.trung_thien_technology.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Page<IProductProjection> findAll(Pageable pageable,Integer type) {
        return iProductRepository.findAllByIsAndDelete(pageable,type);
    }

    @Override
    public List<IProductProjection> findAllByType(Integer type) {
        return iProductRepository.findAllByPC(type);
    }
}
