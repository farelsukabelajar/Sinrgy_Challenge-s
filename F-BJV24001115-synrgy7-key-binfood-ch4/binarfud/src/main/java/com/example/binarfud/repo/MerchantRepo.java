package com.example.binarfud.repo;

import com.example.binarfud.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantRepo extends JpaRepository<Merchant, UUID> {
    Page<Merchant> findByDeletedFalse(Pageable pageable);
    List<Merchant> findByMerchantName(String merchantName);
    List<Merchant> findByMerchantLocation(String location);
}

