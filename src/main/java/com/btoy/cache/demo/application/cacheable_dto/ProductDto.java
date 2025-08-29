package com.btoy.cache.demo.dto;

import com.btoy.cache.demo.cache.Cacheable;
import com.btoy.cache.demo.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

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

    private final CategoryDto category;
}
