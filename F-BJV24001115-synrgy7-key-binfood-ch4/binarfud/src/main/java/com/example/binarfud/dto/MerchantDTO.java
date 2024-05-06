package com.example.binarfud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDTO {
    private UUID merchantId;
    private String merchantName;
    private String merchantLocation;
    private boolean open;
}
