package com.example.trung_thien_technology.service.impl;


import com.example.trung_thien_technology.repository.ICustomerRepository;
import com.example.trung_thien_technology.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;
}
