package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.config.JwtTokenUtil;
import com.example.trung_thien_technology.dto.OrderDetailDTO;
import com.example.trung_thien_technology.model.Customers;
import com.example.trung_thien_technology.model.Users;
import com.example.trung_thien_technology.projection.IOrderDetailProjection;
import com.example.trung_thien_technology.projection.IOrderProjection;
import com.example.trung_thien_technology.service.*;
import com.example.trung_thien_technology.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private IProductService iProductService;
    @Autowired
    private IOrderService iOrderService;


    @PostMapping("/save-order-by-paypal")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @Transactional
    public ResponseEntity<?> saveOrderByPayPal(HttpServletRequest httpServletRequest, @RequestBody List<OrderDetailDTO> orderDetailDTOS) {
        String header = httpServletRequest.getHeader("Authorization");
        if (!header.equals("") && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Users users = iUsersService.findByUsername(username);
            Customers customer = iCustomerService.getCustomerByUserId(users.getId()).get();
            boolean check = iProductService.checkQuantity(orderDetailDTOS);
            if (check) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            this.iShoppingCartService.clearShoppingCart(customer.getId());
            this.iOrderService.saveOrderByPayPal(orderDetailDTOS, customer);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/save-order")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @Transactional
    public ResponseEntity<?> saveOrder(HttpServletRequest httpServletRequest, @RequestBody List<OrderDetailDTO> orderDetailDTOS) {
        String header = httpServletRequest.getHeader("Authorization");
        if (!header.equals("") && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Users users = iUsersService.findByUsername(username);
            Customers customer = iCustomerService.getCustomerByUserId(users.getId()).get();
            boolean check = iProductService.checkQuantity(orderDetailDTOS);
            if (check) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            this.iShoppingCartService.clearShoppingCart(customer.getId());
            this.iOrderService.saveOrder(orderDetailDTOS, customer);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> getAllOrder(HttpServletRequest httpServletRequest, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        String header = httpServletRequest.getHeader("Authorization");
        if (!header.equals("") && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Users users = iUsersService.findByUsername(username);
            Customers customer = iCustomerService.getCustomerByUserId(users.getId()).get();
            Page<IOrderProjection> projectionPage = this.iOrderService.findAllOrder(PageRequest.of(page, 5), customer.getId());
            if (projectionPage.getTotalPages() < page) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(projectionPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}