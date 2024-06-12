package com.example.binarfud.controller;

import com.example.binarfud.model.Product;
import com.example.binarfud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteById(id);
    }

    @GetMapping("/paginated")
    public Page<Product> getProductsPaginated(@RequestParam int page, @RequestParam int size) {
        return productService.findPaginated(page, size);
    }

    @PutMapping("/{id}/price")
    public void updateProductPrice(@PathVariable UUID id, @RequestParam double newPrice) {
        productService.updatePrice(id, newPrice);
    }
}
