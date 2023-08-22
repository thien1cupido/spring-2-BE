package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.projection.IOrderDetailProjection;
import com.example.trung_thien_technology.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-detail")
@CrossOrigin("*")
public class OrderDetailRestController {

    @Autowired
    private IOrderDetailService iOrderDetailService;

    @GetMapping("/list/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> getAllOrderDetail(@PathVariable("id")Integer id) {
        List<IOrderDetailProjection> projectionPage = this.iOrderDetailService.findAllOrderDetail(id);
        return new ResponseEntity<>(projectionPage, HttpStatus.OK);
    }
}
