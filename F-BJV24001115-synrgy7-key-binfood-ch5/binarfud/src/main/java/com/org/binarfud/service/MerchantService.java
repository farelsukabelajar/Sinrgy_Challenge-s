package com.org.binarfud.service;

import com.org.binarfud.dto.MerchantDTO;
import com.org.binarfud.model.Merchant;
import com.org.binarfud.repo.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepo merchantRepo;

    public List<MerchantDTO> getAllMerchants() {
        return merchantRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MerchantDTO> getMerchantById(UUID id) {
        return merchantRepo.findById(id).map(this::convertToDTO);
    }

    public MerchantDTO createMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = convertToEntity(merchantDTO);
        Merchant savedMerchant = merchantRepo.save(merchant);
        return convertToDTO(savedMerchant);
    }

    public Optional<MerchantDTO> updateMerchant(UUID id, MerchantDTO merchantDetails) {
        return merchantRepo.findById(id).map(merchant -> {
            merchant.setName(merchantDetails.getName());
            merchant.setLocation(merchantDetails.getLocation());
            merchant.setOpen(merchantDetails.isOpen());
            return convertToDTO(merchantRepo.save(merchant));
        });
    }

    public void deleteMerchant(UUID id) {
        merchantRepo.deleteById(id);
    }

    private MerchantDTO convertToDTO(Merchant merchant) {
        MerchantDTO dto = new MerchantDTO();
        dto.setId(merchant.getId());
        dto.setName(merchant.getName());
        dto.setLocation(merchant.getLocation());
        dto.setOpen(merchant.isOpen());
        // Optionally map products
        return dto;
    }

    private Merchant convertToEntity(MerchantDTO dto) {
        Merchant merchant = new Merchant();
        merchant.setId(dto.getId());
        merchant.setName(dto.getName());
        merchant.setLocation(dto.getLocation());
        merchant.setOpen(dto.isOpen());
        // Optionally map products
        return merchant;
    }
}
