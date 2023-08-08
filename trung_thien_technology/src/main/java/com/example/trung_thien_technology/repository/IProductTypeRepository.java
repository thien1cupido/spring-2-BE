package com.example.trung_thien_technology.repository;


import com.example.trung_thien_technology.model.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductTypeRepository extends JpaRepository<ProductTypes, Integer> {

    @Query(value = "SELECT * FROM product_type", nativeQuery = true)
    List<ProductTypes> getAllProductType();


}
