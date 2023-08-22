package com.example.trung_thien_technology.repository;

import com.example.trung_thien_technology.model.Products;
import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.projection.IShoppingCartProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Products, Integer> {

    @Query(value = "SELECT p.id AS id, p.name AS name, p.price AS price, i.url AS image \n" +
            "FROM product AS p\n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            "        INNER JOIN  product_types AS pt on p.product_type_id = pt.id\n" +
            "WHERE p.is_delete=false AND  p.product_type_id=:type AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id);", nativeQuery = true)
    Page<IProductProjection> findAllByIsAndDelete(Pageable pageable, @Param("type") Integer type);


    @Query(value = "SELECT p.id AS id, p.name AS name, p.price AS price,p.quantity , i.url AS image \n" +
            "FROM product AS p\n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            "WHERE p.is_delete=false AND  p.product_type_id=:type AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id);", nativeQuery = true)
    List<IProductProjection> findAllByPC(@Param("type") Integer id);

    @Query(value = "SELECT p.id AS id, p.name AS name, p.price AS price, i.url AS image \n" +
            " FROM product AS p \n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            " WHERE p.is_delete=false AND p.name LIKE concat('%',:name_search,'%') AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)",
            countQuery =
                    " SELECT COUNT(*)" +
                            "            FROM product AS p" +
                            "                    INNER JOIN images AS i on p.id = i.product_id" +
                            "            WHERE p.is_delete=false AND p.name LIKE concat('%',:name_search,'%') AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)",
            nativeQuery = true)
    Page<IProductProjection> findAllProduct(Pageable pageable, @Param("name_search") String nameSearch);


    @Query(value = "SELECT p.id AS id, p.name AS name, p.price AS price,p.quantity AS quantity, i.url AS image \n" +
            " FROM product AS p \n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            " WHERE p.is_delete=false AND p.name LIKE concat('%',:name_search,'%') AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id) ORDER BY p.create_time DESC ",
            countQuery =
                    " SELECT COUNT(*)" +
                            "            FROM product AS p" +
                            "                    INNER JOIN images AS i on p.id = i.product_id" +
                            "            WHERE p.is_delete=false AND p.name LIKE concat('%',:name_search,'%') AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id) ORDER BY p.create_time DESC",
            nativeQuery = true)
    Page<IShoppingCartProjection> findAllProductAdmin(Pageable pageable, @Param("name_search") String nameSearch);

    @Query(value = "SELECT p.id  AS id, p.name AS name, p.price AS price, i.url AS image \n" +
            "FROM product AS p\n" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            "        INNER JOIN  product_types AS pt on p.product_type_id = pt.id\n" +
            "WHERE p.is_delete=false AND p.id=:pro_id AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id);", nativeQuery = true)
    Optional<IProductProjection> findProductById(@Param("pro_id") Integer productId);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE product AS p SET p.id_delete=true WHERE p.id=:product_id")
    void deleteProductById(@Param("product_id") Integer id);


    @Query(value = "SELECT * FROM product AS p WHERE p.quantity=0",nativeQuery = true)
    List<Products> findAllByCustomer();
}
