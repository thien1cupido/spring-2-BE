package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.model.ShoppingCart;
import com.example.trung_thien_technology.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    private IShoppingCartService iShoppingCartService;


    @Override
    public void saveShoppingCart(ShoppingCart shoppingCart) {

    }
}
