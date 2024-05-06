package com.example.binarfud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private UUID orderId;
    private LocalDateTime orderTime;
    private String destinationAddress;
    private boolean completed;
    private UUID userId;
    private UUID merchantId;
    private List<OrderDetailDTO> orderDetails;
}
