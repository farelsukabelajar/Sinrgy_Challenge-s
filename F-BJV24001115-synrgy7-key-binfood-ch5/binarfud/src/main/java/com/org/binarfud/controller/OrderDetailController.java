package com.org.binarfud.controller;

import com.org.binarfud.model.OrderDetail;
import com.org.binarfud.repo.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    // Get all order details
    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepo.findAll();
    }

    // Get order detail by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable UUID id) {
        Optional<OrderDetail> orderDetail = orderDetailRepo.findById(id);
        if (orderDetail.isPresent()) {
            return ResponseEntity.ok(orderDetail.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new order detail
    @PostMapping
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailRepo.save(orderDetail);
    }

    // Update existing order detail
    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable UUID id, @RequestBody OrderDetail orderDetailDetails) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepo.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            orderDetail.setQuantity(orderDetailDetails.getQuantity());
            orderDetail.setTotalPrice(orderDetailDetails.getTotalPrice());
            orderDetail.setProduct(orderDetailDetails.getProduct());
            orderDetail.setOrder(orderDetailDetails.getOrder());
            return ResponseEntity.ok(orderDetailRepo.save(orderDetail));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete order detail
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable UUID id) {
        Optional<OrderDetail> orderDetail = orderDetailRepo.findById(id);
        if (orderDetail.isPresent()) {
            orderDetailRepo.delete(orderDetail.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
