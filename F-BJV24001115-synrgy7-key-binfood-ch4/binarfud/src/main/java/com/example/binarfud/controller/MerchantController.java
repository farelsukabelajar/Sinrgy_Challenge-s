package com.example.binarfud.controller;

import com.example.binarfud.dto.MerchantDTO;
import com.example.binarfud.model.Merchant;
import com.example.binarfud.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> getAllMerchants() {
        List<Merchant> merchants = merchantService.getAllMerchants();
        List<MerchantDTO> merchantDTOs = merchants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(merchantDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchantById(@PathVariable UUID id) {
        Merchant merchant = merchantService.getMerchantById(id);
        if (merchant != null) {
            MerchantDTO merchantDTO = convertToDTO(merchant);
            return ResponseEntity.ok(merchantDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<MerchantDTO>> getAllMerchants(Pageable pageable) {
        Page<Merchant> merchants = merchantService.getAllMerchants(pageable);
        Page<MerchantDTO> merchantDTOs = merchants.map(this::convertToDTO);
        return ResponseEntity.ok(merchantDTOs);
    }

    @PostMapping
    public ResponseEntity<MerchantDTO> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        Merchant merchant = convertToEntity(merchantDTO);
        merchantService.createMerchant(merchant);
        return new ResponseEntity<>(merchantDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchantDTO> updateMerchant(@PathVariable UUID id, @RequestBody MerchantDTO merchantDTO) {
        Merchant existingMerchant = merchantService.getMerchantById(id);
        if (existingMerchant != null) {
            Merchant updatedMerchant = convertToEntity(merchantDTO);
            updatedMerchant.setMerchantId(id);
            merchantService.saveOrUpdateMerchant(updatedMerchant);
            return ResponseEntity.ok(merchantDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable UUID id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }

    private MerchantDTO convertToDTO(Merchant merchant) {
        return new MerchantDTO(merchant.getMerchantId(), merchant.getMerchantName(),
                merchant.getMerchantLocation(), merchant.isOpen());
    }

    private Merchant convertToEntity(MerchantDTO merchantDTO) {
        return new Merchant(merchantDTO.getMerchantId(), merchantDTO.getMerchantName(),
                merchantDTO.getMerchantLocation(), merchantDTO.isOpen());
    }
}
