package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.model.Images;
import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.repository.IImagesRepository;
import com.example.trung_thien_technology.repository.IProductRepository;
import com.example.trung_thien_technology.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private IImagesRepository iImagesRepository;

    @Override
    public Page<IProductProjection> findAll(Pageable pageable,Integer type) {
        return iProductRepository.findAllByIsAndDelete(pageable,type);
    }

    @Override
    public List<IProductProjection> findAllByType(Integer type) {
        return iProductRepository.findAllByPC(type);
    }

    @Override
    public Page<IProductProjection> findAllProduct(Pageable pageable,String nameSearch) {
        return iProductRepository.findAllProduct(pageable,nameSearch);
    }

    @Override
    public Optional<IProductProjection> findProductById(Integer productId) {
        return iProductRepository.findProductById(productId);
    }
}
