package com.example.binarfud.service;

import com.example.binarfud.model.OrderDetail;

import java.util.List;
import java.util.UUID;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetails();
    OrderDetail getOrderDetailById(UUID id);
    OrderDetail createOrderDetail(OrderDetail orderDetail);
    OrderDetail updateOrderDetail(UUID id, OrderDetail orderDetail);
    void deleteOrderDetail(UUID id);
    // Tambahkan metode untuk menyimpan atau memperbarui order detail
    OrderDetail saveOrUpdateOrderDetail(OrderDetail orderDetail);
}
