package com.example.trung_thien_technology.repository;

import com.example.trung_thien_technology.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Orders, Integer> {
}
