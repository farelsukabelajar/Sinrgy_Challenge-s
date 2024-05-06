package com.example.binarfud.repo;

import com.example.binarfud.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, UUID> {
    List<Order> findByDeletedFalse();
    List<Order> findByCompleted(boolean completed);
}
