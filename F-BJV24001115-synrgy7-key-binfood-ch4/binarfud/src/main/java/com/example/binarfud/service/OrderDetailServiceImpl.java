package com.example.binarfud.service;

import com.example.binarfud.model.OrderDetail;
import com.example.binarfud.repo.OrderDetailRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        logger.info("Fetching all order details");
        return orderDetailRepo.findByDeletedFalse();
    }

    @Override
    public OrderDetail getOrderDetailById(UUID id) {
        logger.info("Fetching order detail by ID: {}", id);
        return orderDetailRepo.findById(id).orElse(null);
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        logger.info("Creating order detail: {}", orderDetail);
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public OrderDetail updateOrderDetail(UUID id, OrderDetail orderDetail) {
        if (orderDetailRepo.existsById(id)) {
            logger.info("Updating order detail with ID {}: {}", id, orderDetail);
            orderDetail.setOrderDetailId(id);
            return orderDetailRepo.save(orderDetail);
        }
        logger.warn("Order detail with ID {} not found, update operation failed", id);
        return null;
    }

    @Override
    public void deleteOrderDetail(UUID id) {
        if (orderDetailRepo.existsById(id)) {
            logger.info("Deleting order detail with ID: {}", id);
            orderDetailRepo.deleteById(id);
        } else {
            logger.warn("Order detail with ID {} not found, delete operation failed", id);
        }
    }

    @Override
    public OrderDetail saveOrUpdateOrderDetail(OrderDetail orderDetail) {
        logger.info("Saving or updating order detail: {}", orderDetail);
        return orderDetailRepo.save(orderDetail);
    }

}
