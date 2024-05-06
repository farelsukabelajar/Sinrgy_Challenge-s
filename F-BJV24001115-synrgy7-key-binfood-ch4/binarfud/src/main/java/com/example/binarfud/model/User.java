package com.example.binarfud.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@SQLDelete(sql = "update users set deleted = true where id =?")
@SQLRestriction("deleted = false")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="users_id")
    private UUID usersId;

    @Column(name = "username")
    private String username;

    @Column(name = "email_address")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Order> order;

    private boolean deleted = Boolean.FALSE;
}