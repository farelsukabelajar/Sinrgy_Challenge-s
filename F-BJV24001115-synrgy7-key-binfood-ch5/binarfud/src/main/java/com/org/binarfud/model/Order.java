package com.org.binarfud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

import java.util.List;
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", columnDefinition = "uuid", updatable = false)
    private UUID orderId;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "order_time")
    private LocalDate orderTime;

    @Column(name = "complete")
    private Boolean isComplete;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "users_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference(value = "order-orderDetails")
    private List<OrderDetail> orderDetails;

}
