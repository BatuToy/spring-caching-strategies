package com.btoy.cache.demo.application.cacheable_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/*
 * @created 29/08/2025 ~~ 12:24
 * author: batu
 */
@Builder
@Getter
@AllArgsConstructor
public class CategoryDto implements Serializable, Cacheable {
    private final String name;
}
