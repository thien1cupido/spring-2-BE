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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    private IShoppingCartRepository iShoppingCartRepository;

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public void saveShoppingCart(List<ShoppingCartDTO> shoppingCart, Customers customers) {
        List<ShoppingCart> shoppingCarts=iShoppingCartRepository.findShoppingCartByCustomers(customers.getId());
        List<Integer> cartProjectionList = shoppingCarts.stream().map(ShoppingCart::getProductsId).collect(Collectors.toList());
        List<Integer> cartProjectionList1 = shoppingCart.stream().map(ShoppingCartDTO::getId).collect(Collectors.toList());
        for (int i = 0; i < shoppingCart.size(); i++) {
            if (cartProjectionList.stream().noneMatch(shoppingCart.get(i).getId()::equals)) {
                iShoppingCartRepository.save(new ShoppingCart(customers, new Products(shoppingCart.get(i).getId()), shoppingCart.get(i).getQuantity()));
            } else {
                boolean flag=false;
                for (int j = 0; j < shoppingCarts.size(); j++) {
                    if (cartProjectionList1.stream().noneMatch(shoppingCarts.get(i).getProductsId()::equals)) {
                        iShoppingCartRepository.deleteShoppingCartByProduct(customers.getId(),shoppingCarts.get(i).getProductsId());
                        flag=true;
                    }
                }
                if (!flag){
                    iShoppingCartRepository.updateShoppingCart(shoppingCart.get(i).getId(), shoppingCart.get(i).getQuantity(), customers.getId());
                }
            }
        }
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public void clearShoppingCart(Integer customerId) {
        iShoppingCartRepository.clearShoppingCartByCustomer(customerId);
    }

    @Override
    public List<IShoppingCartProjection> findAllShoppingCartByCustomer(Integer id) {
        return iShoppingCartRepository.findShoppingCartByCustomer(id);
    }
}
