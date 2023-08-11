package com.example.trung_thien_technology.repository;

import com.example.trung_thien_technology.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetails,Integer> {
}
