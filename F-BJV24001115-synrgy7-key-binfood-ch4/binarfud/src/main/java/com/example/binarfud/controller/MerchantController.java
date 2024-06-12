package com.example.binarfud.controller;

import com.example.binarfud.model.Merchant;
import com.example.binarfud.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantService.findAll();
    }

    @GetMapping("/{id}")
    public Merchant getMerchantById(@PathVariable UUID id) {
        return merchantService.findById(id);
    }

    @PostMapping
    public Merchant createMerchant(@RequestBody Merchant merchant) {
        return merchantService.save(merchant);
    }

    @DeleteMapping("/{id}")
    public void deleteMerchant(@PathVariable UUID id) {
        merchantService.deleteById(id);
    }

    @GetMapping("/paginated")
    public Page<Merchant> getMerchantsPaginated(@RequestParam int page, @RequestParam int size) {
        return merchantService.findPaginated(page, size);
    }
}
