package com.example.binarfud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private UUID orderDetailId;
    private int quantity;
    private int totalPrice;
    private UUID productId;
    private UUID orderId;
}
