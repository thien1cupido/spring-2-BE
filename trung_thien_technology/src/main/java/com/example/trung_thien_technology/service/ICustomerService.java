package com.example.trung_thien_technology.service;


import com.example.trung_thien_technology.model.Customers;

public interface ICustomerService {


    Customers getCustomerByUserId(Integer id);
}
