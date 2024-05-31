package com.org.binarfud.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class MerchantDTO {
    private UUID id;
    private String name;
    private String location;
    private boolean isOpen;
    private List<ProductDTO> products; // Optional, depending on your use case
}
