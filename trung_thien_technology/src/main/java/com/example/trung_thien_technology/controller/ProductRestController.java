package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.projection.IProductProjection;
import com.example.trung_thien_technology.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<IProductProjection>> getAllProduct(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<IProductProjection> products = this.iProductService.findAllProductAdmin(PageRequest.of(page, 5));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Integer id) {
        Optional<IProductProjection> productProjection=this.iProductService.findProductById(id);
        if (!productProjection.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean check = this.iProductService.deleteProductById(id);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
}
