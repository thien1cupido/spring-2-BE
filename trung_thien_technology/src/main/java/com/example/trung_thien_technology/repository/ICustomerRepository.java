package com.example.trung_thien_technology.repository;


import com.example.trung_thien_technology.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customers, Long> {


    @Query(value = "SELECT * FROM customers AS c WHERE c.is_delete = FALSE", nativeQuery = true)
    List<Customers> getAllCustomer();
}
