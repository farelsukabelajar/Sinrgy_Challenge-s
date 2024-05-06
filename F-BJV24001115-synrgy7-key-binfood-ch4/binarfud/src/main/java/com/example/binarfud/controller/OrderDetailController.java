package com.example.binarfud.controller;

import com.example.binarfud.dto.OrderDetailDTO;
import com.example.binarfud.model.OrderDetail;
import com.example.binarfud.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        List<OrderDetailDTO> orderDetailDTOs = orderDetails.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDetailDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable UUID id) {
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
        if (orderDetail != null) {
            OrderDetailDTO orderDetailDTO = convertToDTO(orderDetail);
            return ResponseEntity.ok(orderDetailDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderDetailDTO> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = convertToEntity(orderDetailDTO);
        orderDetailService.saveOrUpdateOrderDetail(orderDetail);
        return new ResponseEntity<>(orderDetailDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> updateOrderDetail(@PathVariable UUID id, @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetail existingOrderDetail = orderDetailService.getOrderDetailById(id);
        if (existingOrderDetail != null) {
            OrderDetail updatedOrderDetail = convertToEntity(orderDetailDTO);
            updatedOrderDetail.setOrderDetailId(id);
            orderDetailService.saveOrUpdateOrderDetail(updatedOrderDetail);
            return ResponseEntity.ok(orderDetailDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable UUID id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }

    private OrderDetailDTO convertToDTO(OrderDetail orderDetail) {
        return new OrderDetailDTO(orderDetail.getOrderDetailId(), orderDetail.getQty(),
                orderDetail.getTotalPrice(), orderDetail.getProduct().getProductId(),
                orderDetail.getOrder().getOrderId());
    }

    private OrderDetail convertToEntity(OrderDetailDTO orderDetailDTO) {
        // Conversion logic from OrderDetailDTO to OrderDetail entity goes here
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailId(orderDetailDTO.getOrderDetailId());
        orderDetail.setQty(orderDetailDTO.getQuantity());
        orderDetail.setTotalPrice(orderDetailDTO.getTotalPrice());
        // Anda perlu mendapatkan objek Product dan Order dari basis data berdasarkan ID yang tersedia di DTO
        // Misalnya, jika Anda menyimpan ID product dan order di DTO, Anda perlu mendapatkan objek Product dan Order dari service berdasarkan ID-nya.
        // orderDetail.setProduct(product);
        // orderDetail.setOrder(order);
        return orderDetail;
    }
}
