package com.org.binarfud.controller;

import com.org.binarfud.model.User;
import com.org.binarfud.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID; // Import UUID

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) { // Change method signature to accept UUID
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    // Update existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Update properties based on the values received from the requestBody
            user.setUsername(userDetails.getUsername());

            // Check if emailAddress is not null before updating
            if (userDetails.getEmailAddress() != null) {
                user.setEmailAddress(userDetails.getEmailAddress());
            } else {
                // Handle the case where emailAddress is null
                return ResponseEntity.badRequest().build(); // Changed from body("Email address cannot be null")
            }

            user.setPassword(userDetails.getPassword());
            return ResponseEntity.ok(userRepo.save(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) { // Change method signature to accept UUID
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.delete(user.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
