package com.example.trung_thien_technology.repository;

import com.example.trung_thien_technology.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImagesRepository extends JpaRepository<Images, Integer> {
    @Query(nativeQuery = true, value = "SELECT *FROM images AS i WHERE i.product_id=:id_product")
    List<Images> findAllImages(@Param("id_product") Integer idProduct);
}
