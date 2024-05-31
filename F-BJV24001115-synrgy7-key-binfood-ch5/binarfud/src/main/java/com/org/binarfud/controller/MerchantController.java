package com.org.binarfud.controller;

import com.org.binarfud.dto.MerchantDTO;
import com.org.binarfud.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public List<MerchantDTO> getAllMerchants() {
        return merchantService.getAllMerchants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchantById(@PathVariable UUID id) {
        Optional<MerchantDTO> merchantDTO = merchantService.getMerchantById(id);
        return merchantDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MerchantDTO createMerchant(@RequestBody MerchantDTO merchantDTO) {
        return merchantService.createMerchant(merchantDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchantDTO> updateMerchant(@PathVariable UUID id, @RequestBody MerchantDTO merchantDTO) {
        Optional<MerchantDTO> updatedMerchant = merchantService.updateMerchant(id, merchantDTO);
        return updatedMerchant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable UUID id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }
}
