package com.example.binarfud.service;

import com.example.binarfud.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(UUID id);
    Product createProduct(Product product);
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);
    // Tambahkan metode untuk menyimpan atau memperbarui produk
    Product saveOrUpdateProduct(Product product);
}
