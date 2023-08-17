package com.example.trung_thien_technology.service;


import com.example.trung_thien_technology.dto.ShoppingCartDTO;
import com.example.trung_thien_technology.model.Customers;


import com.example.trung_thien_technology.model.ShoppingCart;
import com.example.trung_thien_technology.projection.IShoppingCartProjection;

import java.util.List;
import java.util.Optional;

public interface IShoppingCartService {


    void saveShoppingCart(List<ShoppingCartDTO> shoppingCart, Customers customers);

    void clearShoppingCart(Integer customerId);

    List<IShoppingCartProjection> findAllShoppingCartByCustomer(Integer id);
}
