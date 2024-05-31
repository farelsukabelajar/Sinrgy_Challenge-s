package com.org.binarfud.service;

import com.org.binarfud.dto.OrderDTO;
import com.org.binarfud.model.Order;
import com.org.binarfud.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrderById(UUID id) {
        return orderRepo.findById(id).map(this::convertToDTO);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepo.save(order);
        return convertToDTO(savedOrder);
    }

    public Optional<OrderDTO> updateOrder(UUID id, OrderDTO orderDetails) {
        return orderRepo.findById(id).map(order -> {
            order.setDestinationAddress(orderDetails.getDestinationAddress());
            order.setOrderTime(orderDetails.getOrderTime());
            order.setIsComplete(orderDetails.getIsComplete());
            return convertToDTO(orderRepo.save(order));
        });
    }

    public void deleteOrder(UUID id) {
        orderRepo.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setDestinationAddress(order.getDestinationAddress());
        dto.setOrderTime(order.getOrderTime());
        dto.setIsComplete(order.getIsComplete());
        // Optionally map user and order details
        return dto;
    }

    public Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        order.setDestinationAddress(dto.getDestinationAddress());
        order.setOrderTime(dto.getOrderTime());
        order.setIsComplete(dto.getIsComplete());
        // Optionally map user and order details
        return order;
    }
}
