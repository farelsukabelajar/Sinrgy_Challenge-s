package com.org.binarfud.controller;

import com.org.binarfud.model.Merchant;
import com.org.binarfud.repo.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantRepo merchantRepo;

    // Get all merchants
    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantRepo.findAll();
    }

    // Get merchant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable UUID id) {
        Optional<Merchant> merchant = merchantRepo.findById(id);
        if (merchant.isPresent()) {
            return ResponseEntity.ok(merchant.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new merchant
    @PostMapping
    public Merchant createMerchant(@RequestBody Merchant merchant) {
        return merchantRepo.save(merchant);
    }

    // Update existing merchant
    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable UUID id, @RequestBody Merchant merchantDetails) {
        Optional<Merchant> optionalMerchant = merchantRepo.findById(id);
        if (optionalMerchant.isPresent()) {
            Merchant merchant = optionalMerchant.get();
            merchant.setName(merchantDetails.getName());
            merchant.setLocation(merchantDetails.getLocation());
            merchant.setOpen(merchantDetails.isOpen());
            return ResponseEntity.ok(merchantRepo.save(merchant));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete merchant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable UUID id) {
        Optional<Merchant> merchant = merchantRepo.findById(id);
        if (merchant.isPresent()) {
            merchantRepo.delete(merchant.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
