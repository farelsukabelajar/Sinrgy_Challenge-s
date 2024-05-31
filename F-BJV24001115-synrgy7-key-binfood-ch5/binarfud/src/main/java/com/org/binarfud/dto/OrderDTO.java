package com.org.binarfud.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderDTO {
    private UUID orderId;
    private String destinationAddress;
    private LocalDate orderTime;
    private Boolean isComplete;
    private UserDTO user; // Optional, depending on your use case
    private List<OrderDetailDTO> orderDetails; // Optional, depending on your use case
}
