package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartService iCartService;

}
