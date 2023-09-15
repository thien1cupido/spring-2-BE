package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.config.JwtTokenUtil;
import com.example.trung_thien_technology.model.Customers;
import com.example.trung_thien_technology.model.Users;
import com.example.trung_thien_technology.service.ICustomerService;
import com.example.trung_thien_technology.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/customer")
@CrossOrigin("*")
public class CustomerRestController {

    @Autowired
    private IUsersService iUsersService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ICustomerService iCustomerService;


    @GetMapping("/info")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @Transactional
    public ResponseEntity<?> saveOrderByPayPal(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");
        if (!header.equals("") && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Users users = iUsersService.findByUsername(username);
            Optional<Customers> customer = iCustomerService.getCustomerByUserId(users.getId());
            if (!customer.isPresent()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(customer.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
