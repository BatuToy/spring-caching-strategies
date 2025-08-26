package com.btoy.cache.demo.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "product")
@Table(name = "t_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private UUID id;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "PRODUCT_NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;
}
