package com.org.binarfud.repo;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.org.binarfud.model.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, UUID> {
}
