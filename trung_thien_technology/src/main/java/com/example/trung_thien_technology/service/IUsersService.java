package com.example.trung_thien_technology.service;

import com.example.trung_thien_technology.model.Users;

public interface IUsersService {
    Users findByUsername(String username);

    Users findByEmail(String email);

    Users findById(Integer id);
}
