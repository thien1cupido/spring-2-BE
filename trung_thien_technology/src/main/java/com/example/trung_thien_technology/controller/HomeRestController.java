package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class HomeRestController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/list/PC")
    public ResponseEntity<List<IProductProjection>> getAllPC() {
        List<IProductProjection> products = iProductService.findAllByType(1);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
