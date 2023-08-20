package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.dto.ShoppingCartDTO;
import com.example.trung_thien_technology.model.Customers;
import com.example.trung_thien_technology.model.Products;
import com.example.trung_thien_technology.model.ShoppingCart;
import com.example.trung_thien_technology.projection.IShoppingCartProjection;
import com.example.trung_thien_technology.repository.IShoppingCartRepository;
import com.example.trung_thien_technology.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    private IShoppingCartRepository iShoppingCartRepository;

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public void saveShoppingCart(List<ShoppingCartDTO> shoppingCart, Customers customers) {
        List<ShoppingCart> shoppingCartList = this.iShoppingCartRepository.findShoppingCartByCustomers(customers.getId());
//        data
        List<Integer> cartProjectionList = shoppingCartList.stream().map(ShoppingCart::getProductsId).collect(Collectors.toList());
//        input
        List<Integer> shoppingCarts = shoppingCart.stream().map(ShoppingCartDTO::getId).collect(Collectors.toList());

        for (int i = 0; i < shoppingCarts.size(); i++) {
            if (cartProjectionList.contains(shoppingCarts.get(i))) {
                this.iShoppingCartRepository.updateShoppingCart(shoppingCarts.get(i), shoppingCart.get(i).getQuantity(), customers.getId());
            } else {
                this.iShoppingCartRepository.save(new ShoppingCart(customers, new Products(shoppingCart.get(i).getId()), shoppingCart.get(i).getQuantity()));
            }
        }

        for (int i = 0; i < cartProjectionList.size(); i++) {
            if (!shoppingCarts.contains(cartProjectionList.get(i))) {
                this.iShoppingCartRepository.deleteShoppingCartByProduct(customers.getId(), cartProjectionList.get(i));
            }
        }
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public void clearShoppingCart(Integer customerId) {
        this.iShoppingCartRepository.clearShoppingCartByCustomer(customerId);
    }

    @Override
    public void orderProduct(Integer customerId) {
        this.iShoppingCartRepository.orderedShoppingCartByCustomer(customerId);
    }

    @Override
    public List<IShoppingCartProjection> findAllShoppingCartByCustomer(Integer id) {
        return iShoppingCartRepository.findShoppingCartByCustomer(id);
    }
}
