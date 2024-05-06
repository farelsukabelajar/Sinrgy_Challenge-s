package com.example.binarfud.repo;

import com.example.binarfud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    List<User> findByDeletedFalse();
    User findByUsername(String username);
    User findByEmail(String email);
    
}
