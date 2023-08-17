package com.example.trung_thien_technology.repository;


import com.example.trung_thien_technology.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customers, Long> {

    @Query(nativeQuery = true,value = "SELECT *FROM customers AS c WHERE c.user_id=:user_id")
    Optional<Customers>findCustomersByUser(@Param("user_id")Integer id);

    @Query(value = "SELECT * FROM customers AS c WHERE c.is_delete = FALSE", nativeQuery = true)
    List<Customers> getAllCustomer();
}
