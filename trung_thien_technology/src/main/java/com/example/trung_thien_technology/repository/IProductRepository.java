package com.example.trung_thien_technology.repository;

import com.example.trung_thien_technology.model.Products;
import com.example.trung_thien_technology.projection.IProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Products, Integer> {

    @Query(value = "SELECT p.id, p.name, p.price, i.url \n" +
            "FROM product AS p\n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            "        INNER JOIN  product_types AS pt on p.product_type_id = pt.id\n" +
            "WHERE p.is_delete=false AND  p.product_type_id=:type AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id);", nativeQuery = true)
    Page<IProductProjection> findAllByIsAndDelete(Pageable pageable, @Param("type") Integer type);


    @Query(value = "SELECT p.id AS id, p.name AS name, p.price AS price, i.url AS image \n" +
            "FROM product AS p\n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            "        INNER JOIN  product_types AS pt on p.product_type_id = pt.id\n" +
            "WHERE p.is_delete=false AND  p.product_type_id=:type AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id);", nativeQuery = true)
    List<IProductProjection> findAllByPC(@Param("type")Integer id);

    @Query(value = "SELECT p.id AS id, p.name AS name, p.price AS price, i.url AS image \n" +
            " FROM product AS p \n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            "        INNER JOIN  product_types AS pt on p.product_type_id = pt.id\n" +
            " WHERE p.is_delete=false AND p.name LIKE concat('%',:name_search,'%') AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)",
            countQuery =
                    " SELECT COUNT(*)" +
                    "            FROM product AS p" +
                    "                    INNER JOIN images AS i on p.id = i.product_id" +
                    "                    INNER JOIN  product_types AS pt on p.product_type_id = pt.id" +
                    "            WHERE p.is_delete=false AND p.name LIKE concat('%',:name_search,'%') AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)",
            nativeQuery = true)
    Page<IProductProjection> findAllProduct(Pageable pageable,@Param("name_search")String nameSearch);

    @Query(value = "SELECT p.id  AS id, p.name AS name, p.price AS price, i.url AS image \n" +
            "FROM product AS p\n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            "        INNER JOIN  product_types AS pt on p.product_type_id = pt.id\n" +
            "WHERE p.is_delete=false AND p.id=:pro_id AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id);", nativeQuery = true)
    Optional<IProductProjection> findProductById(@Param("pro_id")Integer productId);
}
