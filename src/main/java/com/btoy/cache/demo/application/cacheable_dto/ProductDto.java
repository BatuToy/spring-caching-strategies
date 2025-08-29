package com.btoy.cache.demo.application.cacheable_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @created 29/08/2025 ~~ 12:23
 * author: batu
 */

@Builder
@Getter
@AllArgsConstructor
public class ProductDto implements Cacheable, Serializable {

    private final String sku;

    private final String name;

    private final BigDecimal price;

}
