package com.example.trung_thien_technology.service;


import com.example.trung_thien_technology.model.Images;
import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.projection.IShoppingCartProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<IProductProjection> findAll(Pageable pageable, Integer type);

    List<IProductProjection> findAllByType(Integer type);

    Page<IProductProjection> findAllProduct(Pageable pageable, String nameSearch);
    Page<IShoppingCartProjection> findAllProductAdmin(Pageable pageable,String nameSearch);
    Optional<IProductProjection> findProductById(Integer productId);

    boolean deleteProductById(Integer id);
}
