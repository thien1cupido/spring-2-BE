package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.model.Images;
import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/list")
    public ResponseEntity<Page<IProductProjection>> getAllProduct(@RequestParam(value = "page", defaultValue = "0") Integer page,@RequestParam("nameSearch")String nameSearch) {
        Page<IProductProjection> products = iProductService.findAllProduct(PageRequest.of(page, 25),nameSearch);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IProductProjection> getProductById(@PathVariable(value = "id") Integer id) {
        Optional<IProductProjection> products = iProductService.findProductById(id);
        return products.map(iProductProjection -> new ResponseEntity<>(iProductProjection, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
