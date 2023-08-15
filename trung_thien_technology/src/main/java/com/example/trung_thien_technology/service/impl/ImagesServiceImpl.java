package com.example.trung_thien_technology.service.impl;

import com.example.trung_thien_technology.model.Images;
import com.example.trung_thien_technology.repository.IImagesRepository;
import com.example.trung_thien_technology.service.IImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImagesServiceImpl implements IImagesService {

    @Autowired
    private IImagesRepository iImagesRepository;

    @Override
    public List<Images> findAllImages(Integer id) {
        return iImagesRepository.findAllImages(id);
    }
}
