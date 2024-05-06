package com.example.binarfud.service;

import com.example.binarfud.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface MerchantService {
    Page<Merchant> getAllMerchants(Pageable pageable);
    List<Merchant> getAllMerchants();
    Merchant getMerchantById(UUID id);
    Merchant createMerchant(Merchant merchant);
    void saveOrUpdateMerchant(Merchant merchant);
    void deleteMerchant(UUID id);
}
