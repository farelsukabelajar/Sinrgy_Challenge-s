package com.example.binarfud.service;

import com.example.binarfud.model.Order;
import com.example.binarfud.repo.OrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public List<Order> getAllOrders() {
        logger.info("Fetching all orders");
        return orderRepo.findByDeletedFalse();
    }

    @Override
    public Order getOrderById(UUID id) {
        logger.info("Fetching order by ID: {}", id);
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        logger.info("Creating order: {}", order);
        return orderRepo.save(order);
    }

    @Override
    public Order updateOrder(UUID id, Order order) {
        if (orderRepo.existsById(id)) {
            logger.info("Updating order with ID {}: {}", id, order);
            order.setOrderId(id);
            return orderRepo.save(order);
        }
        logger.warn("Order with ID {} not found, update operation failed", id);
        return null;
    }

    @Override
    public void deleteOrder(UUID id) {
        if (orderRepo.existsById(id)) {
            logger.info("Deleting order with ID: {}", id);
            orderRepo.deleteById(id);
        } else {
            logger.warn("Order with ID {} not found, delete operation failed", id);
        }
    }
}
