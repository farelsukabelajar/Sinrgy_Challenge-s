package com.example.binarfud.repo;

import com.example.binarfud.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, UUID> {
    List<OrderDetail> findByDeletedFalse();
    List<OrderDetail> findByProductProductId(UUID productId);
    List<OrderDetail> findByOrderOrderId(UUID orderId);
}
