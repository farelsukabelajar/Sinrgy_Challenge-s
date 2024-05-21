package com.org.binarfud.service;

import com.org.binarfud.repo.OrderRepo;

import jakarta.transaction.Transactional;

import com.org.binarfud.repo.OrderDetailRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.binarfud.model.Order;
import com.org.binarfud.model.OrderDetail;

@Service
public class OrderDetailService {
    
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Transactional
    public OrderDetail createOrderDetail(OrderDetail orderDetail, Order order) {
        // Save the order first
        Order savedOrder = orderRepo.save(order);
        
        // Set the saved order to orderDetail
        orderDetail.setOrder(savedOrder);
        
        // Now save the orderDetail
        return orderDetailRepo.save(orderDetail);
    }
}
