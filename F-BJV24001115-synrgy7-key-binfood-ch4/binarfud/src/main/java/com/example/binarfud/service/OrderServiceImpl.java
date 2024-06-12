package com.example.binarfud.service;

import com.example.binarfud.model.Order;
import com.example.binarfud.repository.OrderRepository;
import com.example.binarfud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        logger.info("Fetching all orders");
        return orderRepository.findAll();
    }

    @Override
    public Order findById(UUID id) {
        logger.debug("Fetching order with id: {}", id);
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        logger.info("Saving order with id: {}", order.getOrderId());
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(UUID id) {
        logger.warn("Deleting order with id: {}", id);
        orderRepository.deleteById(id);
    }

    @Override
    public Page<Order> findPaginated(int page, int size) {
        logger.debug("Fetching orders with pagination - page: {}, size: {}", page, size);
        return orderRepository.findAll(PageRequest.of(page, size));
    }
}
