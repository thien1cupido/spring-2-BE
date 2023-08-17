package com.example.trung_thien_technology.service;


import com.example.trung_thien_technology.model.Customers;

import java.util.Optional;

public interface ICustomerService {


    Optional<Customers> getCustomerByUserId(Integer id);
}
