package com.example.binarfud.service;

import com.example.binarfud.model.Merchant;
import java.util.List;
import java.util.UUID;

public interface MerchantService {
    List<Merchant> getAllMerchants();
    Merchant getMerchantById(UUID id);
    Merchant createMerchant(Merchant merchant);
    void saveOrUpdateMerchant(Merchant merchant);
    void deleteMerchant(UUID id);
}
