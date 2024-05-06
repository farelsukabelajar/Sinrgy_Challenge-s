package com.example.binarfud.service;

import com.example.binarfud.model.Order;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(UUID id);
    Order createOrder(Order order);
    Order updateOrder(UUID id, Order order);
    void deleteOrder(UUID id);
}
