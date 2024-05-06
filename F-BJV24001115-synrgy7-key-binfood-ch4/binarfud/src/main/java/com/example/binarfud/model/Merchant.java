package com.example.binarfud.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.util.List;
import java.util.UUID;
@Data
@Entity
@Table(name = "merchant")
@SQLDelete(sql = "update merchant set deleted = true where id =?")
@SQLRestriction("deleted = false")

public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "merchant_id")
    private UUID merchantId;

    @Column(name="merchant_name")
    private String merchantName;

    @Column(name = "merchant_location")
    private String merchantLocation;

    @Column(name = "open")
    private boolean open;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Product> menuItems;

    private boolean deleted = Boolean.FALSE;

    public Merchant() {
    }

    public Merchant(UUID merchantId, String merchantName, String merchantLocation, boolean open) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.merchantLocation = merchantLocation;
        this.open = open;
    }
}
