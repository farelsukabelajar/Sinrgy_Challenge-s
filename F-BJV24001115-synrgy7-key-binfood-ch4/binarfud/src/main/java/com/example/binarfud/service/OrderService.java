package com.example.binarfud.service;

import com.example.binarfud.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> findAll();
    Order findById(UUID id);
    Order save(Order order);
    void deleteById(UUID id);
    Page<Order> findPaginated(int page, int size);
}
