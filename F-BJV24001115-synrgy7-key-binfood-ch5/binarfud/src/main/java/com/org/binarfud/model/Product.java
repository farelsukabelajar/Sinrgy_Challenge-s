package com.org.binarfud.model;

import java.util.List;
import java.util.UUID;
import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "product-orderDetails")
    private List<OrderDetail> orderDetails;
}
