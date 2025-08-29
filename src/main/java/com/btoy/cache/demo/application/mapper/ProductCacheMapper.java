package com.btoy.cache.demo.application.mapper;

import com.btoy.cache.demo.application.cacheable_dto.CategoryDto;
import com.btoy.cache.demo.application.cacheable_dto.ProductDto;
import com.btoy.cache.demo.entity.Product;

/*
 * @created 29/08/2025 ~~ 15:57
 * author: batu
 */
public final class ProductCacheMapper {

    private ProductCacheMapper() {
        throw new UnsupportedOperationException("");
    }

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .sku(product.getSku())
                .price(product.getPrice())
                .build();
    }
}
