package com.org.binarfud.service;

import com.org.binarfud.dto.OrderDetailDTO;
import com.org.binarfud.model.Order;
import com.org.binarfud.model.OrderDetail;
import com.org.binarfud.repo.OrderDetailRepo;
import com.org.binarfud.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OrderDetailService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Transactional
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO, Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        Order savedOrder = orderRepo.save(order);
        OrderDetail orderDetail = convertToEntity(orderDetailDTO);
        orderDetail.setOrder(savedOrder);
        
        OrderDetail savedOrderDetail = orderDetailRepo.save(orderDetail);
        return convertToDTO(savedOrderDetail);
    }

    private OrderDetailDTO convertToDTO(OrderDetail orderDetail) {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(orderDetail.getId());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setTotalPrice(orderDetail.getTotalPrice());
        // Optionally map product and order
        return dto;
    }

    private OrderDetail convertToEntity(OrderDetailDTO dto) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(dto.getId());
        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setTotalPrice(dto.getTotalPrice());
        // Optionally map product and order
        return orderDetail;
    }
}
