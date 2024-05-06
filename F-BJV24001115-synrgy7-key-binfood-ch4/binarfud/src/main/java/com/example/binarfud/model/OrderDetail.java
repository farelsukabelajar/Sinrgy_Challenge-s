package com.example.binarfud.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
@SQLDelete(sql = "update order_detail set deleted = true where id =?")
@SQLRestriction("deleted = false")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="order_detail_id")
    private UUID orderDetailId;

    @Column(name = "quantity")
    private int qty;

    @Column(name = "total_price")
    private int totalPrice;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    private Order order;

    private boolean deleted = Boolean.FALSE;

    public void addQty(int qty) {
        this.qty += qty;
    }
}
