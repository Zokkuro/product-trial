package com.back.producttrial.repository.entity;

import com.back.producttrial.enumeration.InventoryReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    private String internalReference;
    private Long shellId;
    @Enumerated(EnumType.STRING)
    private InventoryReference inventoryReference;
    private Integer rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
