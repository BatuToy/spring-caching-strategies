package com.btoy.cache.demo.rest;

import com.btoy.cache.demo.dto.ProductResponseDto;
import com.btoy.cache.demo.service.CaffeineCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final CaffeineCacheService caffeineCacheService;

    @GetMapping(value = "/get")
    public ResponseEntity<ProductResponseDto> retrieveProductById(@RequestParam(value = "id") String productId) {
        return new ResponseEntity<>(caffeineCacheService.retrieveProductById(productId), HttpStatus.valueOf(200));
    }

}
