package com.example.binarfud.service;

import com.example.binarfud.model.User;
import com.example.binarfud.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepo.findByDeletedFalse();
    }

    @Override
    public User getUserById(UUID id) {
        logger.info("Fetching user by ID: {}", id);
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        logger.info("Creating user: {}", user);
        return userRepo.save(user);
    }

    @Override
    public User updateUser(UUID id, User user) {
        if (userRepo.existsById(id)) {
            logger.info("Updating user with ID {}: {}", id, user);
            user.setUsersId(id);
            return userRepo.save(user);
        } else {
            logger.warn("User with ID {} not found, update operation failed", id);
            return null;
        }
    }

    @Override
    public void deleteUser(UUID id) {
        if (userRepo.existsById(id)) {
            logger.info("Deleting user with ID: {}", id);
            userRepo.deleteById(id);
        } else {
            logger.warn("User with ID {} not found, delete operation failed", id);
        }
    }

    @Override
    public void saveOrUpdateUser(User user) {
        logger.info("Saving or updating user: {}", user);
        userRepo.save(user);
    }
}
