package com.example.trung_thien_technology.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="customer_id")
    private Customers customers;

    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime createTime;
}
