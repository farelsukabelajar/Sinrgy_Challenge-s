package com.example.binarfud.controller;

import com.example.binarfud.dto.OrderDTO;
import com.example.binarfud.model.Order;
import com.example.binarfud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable UUID id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            OrderDTO orderDTO = convertToDTO(order);
            return ResponseEntity.ok(orderDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        orderService.createOrder(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable UUID id, @RequestBody OrderDTO orderDTO) {
        Order existingOrder = orderService.getOrderById(id);
        if (existingOrder != null) {
            Order updatedOrder = convertToEntity(orderDTO);
            updatedOrder.setOrderId(id);
            orderService.updateOrder(id, updatedOrder);
            return ResponseEntity.ok(orderDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(order.getOrderId(), order.getOrderTime(),
                order.getDestinationAddress(), order.isCompleted(),
                order.getUser().getUsersId(), order.getMerchant().getMerchantId(),
                null); // List of OrderDetailDTO can be added here
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setOrderTime(orderDTO.getOrderTime());
        order.setDestinationAddress(orderDTO.getDestinationAddress());
        order.setCompleted(orderDTO.isCompleted());
        return order;
    }
    
}
