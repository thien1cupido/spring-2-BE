package com.example.trung_thien_technology.repository;

import com.example.trung_thien_technology.model.OrderDetails;
import com.example.trung_thien_technology.projection.IOrderDetailProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IOrderDetailRepository extends JpaRepository<OrderDetails, Integer> {

    @Query(value = "SELECT od.id AS id,od.product_id AS productId,i.url AS image,p.name AS productName,od.price AS price,od.quantity AS quantity FROM order_detail AS od " +
            " INNER JOIN orders AS o ON o.id=od.order_id" +
            " INNER JOIN customers AS c ON c.id=o.customer_id" +
            " INNER JOIN product AS p ON p.id=od.product_id" +
            " INNER JOIN images AS i ON p.id=i.product_id" +
            " WHERE od.order_id=:order_id AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)",
            countQuery = "SELECT COUNT(*) FROM order_detail AS od" +
                    "           INNER JOIN orders AS o ON o.id=od.order_id" +
                    "           INNER JOIN customers AS c ON c.id=o.customer_id" +
                    "           INNER JOIN product AS p ON p.id=od.product_id" +
                    "           INNER JOIN images AS i ON p.id=i.product_id" +
                    "           WHERE od.order_id=:order_id AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)", nativeQuery = true)
    Page<IOrderDetailProjection> findAllOrderDetail(Pageable pageable, @Param("order_id") Integer orderId);

    @Query(value = "SELECT od.id AS id,od.product_id AS productId,i.url AS image,p.name AS productName,od.price AS price,od.quantity AS quantity FROM order_detail AS od " +
            " INNER JOIN orders AS o ON o.id=od.order_id" +
            " INNER JOIN customers AS c ON c.id=o.customer_id" +
            " INNER JOIN product AS p ON p.id=od.product_id" +
            " INNER JOIN images AS i ON p.id=i.product_id" +
            " WHERE od.order_id=:order_id AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)",
            countQuery = "SELECT COUNT(*) FROM order_detail AS od" +
                    "           INNER JOIN orders AS o ON o.id=od.order_id" +
                    "           INNER JOIN customers AS c ON c.id=o.customer_id" +
                    "           INNER JOIN product AS p ON p.id=od.product_id" +
                    "           INNER JOIN images AS i ON p.id=i.product_id" +
                    "           WHERE od.order_id=:order_id AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)", nativeQuery = true)
    List<IOrderDetailProjection> findAllOrderDetail(@Param("order_id") Integer orderId);
}
