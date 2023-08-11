package com.example.trung_thien_technology.service;


import com.example.trung_thien_technology.projection.IProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<IProductProjection> findAll(Pageable pageable,Integer type);

    List<IProductProjection> findAllByType(Integer type);
}
