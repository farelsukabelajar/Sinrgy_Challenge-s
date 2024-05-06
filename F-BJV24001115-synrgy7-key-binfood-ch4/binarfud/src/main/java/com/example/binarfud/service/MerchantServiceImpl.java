package com.example.binarfud.service;

import com.example.binarfud.model.Merchant;
import com.example.binarfud.repo.MerchantRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantRepo merchantRepo;

    @Override
    public List<Merchant> getAllMerchants() {
        logger.info("Fetching all merchants");
        return merchantRepo.findByDeletedFalse();
    }

    @Override
    public Merchant getMerchantById(UUID id) {
        logger.info("Fetching merchant by ID: {}", id);
        return merchantRepo.findById(id).orElse(null);
    }

    @Override
    public Merchant createMerchant(Merchant merchant) {
        logger.info("Creating merchant: {}", merchant);
        return merchantRepo.save(merchant);
    }

    @Override
    public void saveOrUpdateMerchant(Merchant merchant) {
        logger.info("Saving or updating merchant: {}", merchant);
        merchantRepo.save(merchant);
    }

    @Override
    public void deleteMerchant(UUID id) {
        if (merchantRepo.existsById(id)) {
            logger.info("Deleting merchant with ID: {}", id);
            merchantRepo.deleteById(id);
        } else {
            logger.warn("Merchant with ID {} not found, delete operation failed", id);
        }
    }

    public Page<Merchant> getAllMerchants(Pageable pageable) {
        logger.info("Fetching all merchants with pagination");
        return merchantRepo.findByDeletedFalse(pageable);
    }

}
