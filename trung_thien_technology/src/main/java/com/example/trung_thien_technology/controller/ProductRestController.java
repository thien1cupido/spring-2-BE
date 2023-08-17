package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.projection.IShoppingCartProjection;
import com.example.trung_thien_technology.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<IShoppingCartProjection>> getAllProduct(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam("nameSearch")String nameSearch) {
        Page<IShoppingCartProjection> products = iProductService.findAllProductAdmin(PageRequest.of(page, 5),nameSearch);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllProduct(@PathVariable(value = "id") Integer id) {
        boolean check = iProductService.deleteProductById(id);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
}
