package com.example.trung_thien_technology.repository;

import com.example.trung_thien_technology.model.Orders;
import com.example.trung_thien_technology.projection.IOrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "SELECT o.id AS id,o.order_code AS orderCode,o.create_time AS createTime,pt.name AS paymentType,o.total_price AS totalPrice,o.payment_status AS paymentStatus FROM orders AS o " +
            " INNER JOIN payment_types AS pt ON pt.id=o.payment_type_id" +
            " INNER JOIN customers AS c ON c.id=o.customer_id" +
            " WHERE o.customer_id=:customer_id" +
            " ORDER BY o.create_time DESC ", nativeQuery = true)
    Page<IOrderProjection> findAllByCreateTime(Pageable pageable, @Param("customer_id") Integer customerId);
}
