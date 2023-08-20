package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.config.JwtTokenUtil;
import com.example.trung_thien_technology.dto.ShoppingCartDTO;
import com.example.trung_thien_technology.model.Customers;
import com.example.trung_thien_technology.model.Products;
import com.example.trung_thien_technology.model.ShoppingCart;
import com.example.trung_thien_technology.model.Users;
import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.projection.IShoppingCartProjection;
import com.example.trung_thien_technology.service.ICustomerService;
import com.example.trung_thien_technology.service.IProductService;
import com.example.trung_thien_technology.service.IShoppingCartService;
import com.example.trung_thien_technology.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "*")
@RequestMapping("/api/shopping-cart")
public class ShoppingCartRestController {
    @Autowired
    private IShoppingCartService iShoppingCartService;
    @Autowired
    private IUsersService iUsersService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<List<IShoppingCartProjection>> getAllShoppingCart(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");
        if (!header.equals("") && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Users users = iUsersService.findByUsername(username);
            Customers customer = iCustomerService.getCustomerByUserId(users.getId()).get();
            List<IShoppingCartProjection> shoppingCartList = iShoppingCartService.findAllShoppingCartByCustomer(customer.getId());
            return new ResponseEntity<>(shoppingCartList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<List<IShoppingCartProjection>> saveShoppingCart(HttpServletRequest httpServletRequest, @RequestBody List<ShoppingCartDTO> shoppingCartDTO) {
        String header = httpServletRequest.getHeader("Authorization");
        if (!header.equals("") && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Users users = iUsersService.findByUsername(username);
            Customers customer = iCustomerService.getCustomerByUserId(users.getId()).get();
            if (shoppingCartDTO.isEmpty()){
                iShoppingCartService.clearShoppingCart(customer.getId());
            }
            iShoppingCartService.saveShoppingCart(shoppingCartDTO,customer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
