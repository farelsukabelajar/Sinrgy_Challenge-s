package com.example.binarfud.service;

import com.example.binarfud.model.Product;
import com.example.binarfud.repo.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        return productRepo.findByDeletedFalse();
    }

    @Override
    public Product getProductById(UUID id) {
        logger.info("Fetching product by ID: {}", id);
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        logger.info("Creating product: {}", product);
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(UUID id, Product product) {
        if (productRepo.existsById(id)) {
            logger.info("Updating product with ID {}: {}", id, product);
            product.setProductId(id);
            return productRepo.save(product);
        }
        logger.warn("Product with ID {} not found, update operation failed", id);
        return null;
    }

    @Override
    public void deleteProduct(UUID id) {
        if (productRepo.existsById(id)) {
            logger.info("Deleting product with ID: {}", id);
            productRepo.deleteById(id);
        } else {
            logger.warn("Product with ID {} not found, delete operation failed", id);
        }
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        logger.info("Saving or updating product: {}", product);
        return productRepo.save(product);
    }
}
