package com.example.trung_thien_technology.controller;

import com.example.trung_thien_technology.model.Images;
import com.example.trung_thien_technology.service.IImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin("*")
public class ImagesRestController {
    @Autowired
    private IImagesService iImagesService;

    @GetMapping("/list/{id}")
    public ResponseEntity<List<Images>> getAllImages(@PathVariable("id") Integer id) {
        List<Images> images = iImagesService.findAllImages(id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
