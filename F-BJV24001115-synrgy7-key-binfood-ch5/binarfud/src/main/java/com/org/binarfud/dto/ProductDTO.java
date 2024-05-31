package com.org.binarfud.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductDTO {
    private UUID id;
    private String name;
    private double price;
    private MerchantDTO merchant; // Optional, depending on your use case
    private List<OrderDetailDTO> orderDetails; // Optional, depending on your use case
}
