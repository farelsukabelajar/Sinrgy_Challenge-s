package com.org.binarfud.service;

import com.org.binarfud.dto.MerchantDTO;
import com.org.binarfud.dto.ProductDTO;
import com.org.binarfud.model.Merchant;
import com.org.binarfud.model.Product;
import com.org.binarfud.repo.MerchantRepo;
import com.org.binarfud.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private MerchantRepo merchantRepo;

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(UUID id) {
        return productRepo.findById(id).map(this::convertToDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepo.save(product);
        return convertToDTO(savedProduct);
    }

    public Optional<ProductDTO> updateProduct(UUID id, ProductDTO productDetails) {
        return productRepo.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            // Fetch the Merchant entity using the ID from the MerchantDTO
            Merchant merchant = merchantRepo.findById(productDetails.getMerchant().getId())
                                            .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));
            product.setMerchant(merchant);
            return convertToDTO(productRepo.save(product));
        });
    }

    public void deleteProduct(UUID id) {
        productRepo.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        // Map the Merchant entity to MerchantDTO
        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setId(product.getMerchant().getId());
        merchantDTO.setName(product.getMerchant().getName());
        merchantDTO.setLocation(product.getMerchant().getLocation());
        merchantDTO.setOpen(product.getMerchant().isOpen());
        dto.setMerchant(merchantDTO);
        // Optionally map order details
        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        // Fetch the Merchant entity using the ID from the MerchantDTO
        Merchant merchant = merchantRepo.findById(dto.getMerchant().getId())
                                        .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));
        product.setMerchant(merchant);
        // Optionally map order details
        return product;
    }
}
