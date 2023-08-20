package com.example.trung_thien_technology.service;


import com.example.trung_thien_technology.dto.ShoppingCartDTO;
import com.example.trung_thien_technology.model.Customers;



import com.example.trung_thien_technology.projection.IShoppingCartProjection;

import java.util.List;


public interface IShoppingCartService {


    void saveShoppingCart(List<ShoppingCartDTO> shoppingCart, Customers customers);

    void clearShoppingCart(Integer customerId);

    void orderProduct(Integer customerId);


    List<IShoppingCartProjection> findAllShoppingCartByCustomer(Integer id);
}
