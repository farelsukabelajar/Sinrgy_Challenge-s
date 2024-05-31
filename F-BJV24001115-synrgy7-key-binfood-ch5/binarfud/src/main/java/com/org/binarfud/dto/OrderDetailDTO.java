package com.org.binarfud.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private UUID id;
    private int quantity;
    private double totalPrice;
    private ProductDTO product; // Optional, depending on your use case
    private OrderDTO order; // Optional, depending on your use case
}
