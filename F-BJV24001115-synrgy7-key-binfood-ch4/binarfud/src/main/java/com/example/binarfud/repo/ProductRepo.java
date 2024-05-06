package com.example.binarfud.repo;

import com.example.binarfud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
    List<Product> findByDeletedFalse();
    List<Product> findByProductName(String productName);
}
