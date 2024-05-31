package com.org.binarfud.controller;

import com.org.binarfud.dto.OrderDetailDTO;
import com.org.binarfud.model.Order;
import com.org.binarfud.service.OrderDetailService;
import com.org.binarfud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/{orderId}")
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@PathVariable UUID orderId, @RequestBody OrderDetailDTO orderDetailDTO) {
        Optional<Order> order = orderService.getOrderById(orderId).map(orderDTO -> orderService.convertToEntity(orderDTO));
        if (!order.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        OrderDetailDTO createdOrderDetail = orderDetailService.createOrderDetail(orderDetailDTO, order.get());
        return ResponseEntity.ok(createdOrderDetail);
    }
}
