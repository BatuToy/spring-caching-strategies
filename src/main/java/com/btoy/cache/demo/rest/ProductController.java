package com.btoy.cache.demo.rest;

import com.btoy.cache.demo.application.cacheable_dto.ProductDto;
import com.btoy.cache.demo.application.port.in.ApplicationService;
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

    private final ApplicationService level1;
    private final ApplicationService level2;

    @GetMapping(value = "/l1")
    public ResponseEntity<ProductDto> retrieveProductById(@RequestParam(value = "id") String productId) {
        return new ResponseEntity<>(level1.getProductById(productId), HttpStatus.valueOf(200));
    }

    @GetMapping(value = "l2")
    public ResponseEntity<ProductDto> retrieveProductByIdL2(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(level2.getProductById(id));
    }

}
