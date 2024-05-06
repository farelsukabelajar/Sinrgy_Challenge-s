package com.example.binarfud.service;

import com.example.binarfud.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(UUID id);
    User createUser(User user);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
    void saveOrUpdateUser(User user); // tambahkan metode saveOrUpdateUser di sini
}
