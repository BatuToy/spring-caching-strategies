package com.btoy.cache.demo.entity;

/*
 * @created 27/08/2025 ~~ 01:12
 * author: batu
 */

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@NamedEntityGraph(name = "Category.products",
        attributeNodes = {
                @NamedAttributeNode(value = "products")
        })
@Entity(name = "category")
@Table(name = "t_category")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Cacheable
@org.hibernate.annotations.Cache(
        region = "category",
        usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category {

    @Column(name = "CATEGORY_ID")
    @Id
    private String id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products;

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "").toLowerCase(new Locale("en", "EN"));
    }

}
