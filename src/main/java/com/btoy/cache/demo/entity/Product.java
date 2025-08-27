package com.btoy.cache.demo.entity;

import jakarta.persistence.*; // this package provides a L2 cache by JPA !
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.UUID;

@Entity(name = "product")
@Table(name = "t_product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Cacheable
@org.hibernate.annotations.Cache(
        region = "product",
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "PRODUCT_NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    private Category category;

    // Insert's could block this @PrePersist!
    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "").toLowerCase(new Locale("en", "EN"));
    }
}
