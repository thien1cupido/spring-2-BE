package com.example.trung_thien_technology.service;

import com.example.trung_thien_technology.model.Images;

import java.util.List;

public interface IImagesService {

    List<Images> findAllImages(Integer id);
}
