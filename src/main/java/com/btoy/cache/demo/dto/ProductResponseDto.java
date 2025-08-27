package com.btoy.cache.demo.dto;

/*
 * @created 27/08/2025 ~~ 01:35
 * author: batu
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProductResponseDto {

    private final String name;
    private final String skuCode;
}
