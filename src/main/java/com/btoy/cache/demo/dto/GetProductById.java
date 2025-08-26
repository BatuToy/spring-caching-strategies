package com.btoy.cache.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GetProductById {

    private final UUID productId;
}
