package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.config.JwtTokenUtil;
import com.example.trung_thien_technology.dto.OrderDetailDTO;
import com.example.trung_thien_technology.model.Customers;
import com.example.trung_thien_technology.model.Users;
import com.example.trung_thien_technology.service.ICustomerService;
import com.example.trung_thien_technology.service.IOrderService;
import com.example.trung_thien_technology.service.IShoppingCartService;
import com.example.trung_thien_technology.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderRestController {
    @Autowired
    private IShoppingCartService iShoppingCartService;
    @Autowired
    private IUsersService iUsersService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IOrderService iOrderService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @Transactional
    public ResponseEntity<?> saveOrder(HttpServletRequest httpServletRequest, @RequestBody List<OrderDetailDTO> orderDetailDTOS) {
        String header = httpServletRequest.getHeader("Authorization");
        if (!header.equals("") && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Users users = iUsersService.findByUsername(username);
            Customers customer = iCustomerService.getCustomerByUserId(users.getId()).get();
            this.iShoppingCartService.orderProduct(customer.getId());
            this.iOrderService.saveOrder(orderDetailDTOS, customer);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}