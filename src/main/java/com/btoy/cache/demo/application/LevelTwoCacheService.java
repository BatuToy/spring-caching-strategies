package com.btoy.cache.demo.application;

import com.btoy.cache.demo.application.port.in.ApplicationService;
import com.btoy.cache.demo.application.port.out.ProductCachePort;
import com.btoy.cache.demo.application.cacheable_dto.CategoryDto;
import com.btoy.cache.demo.application.cacheable_dto.ProductDto;
import com.btoy.cache.demo.dao.ProductRepository;
import com.btoy.cache.demo.entity.Product;
import com.btoy.cache.demo.exception.SourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * @created 26/08/2025 ~~ 20:25
 * author: batu
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ApplicationService {

    private final ProductCachePort productCachePort;
    private final ProductRepository productRepository;

    @Override
    public ProductDto getProductById(String id) {
        Optional<ProductDto> productDto = productCachePort.get(id);
        if (productDto.isEmpty()) {
            Optional<Product> optProduct = productRepository.findById(id);
            if (optProduct.isPresent()) {
                Product product = optProduct.get();
                ProductDto dto = ProductDto.builder()
                        .name(product.getName())
                        .sku(product.getSku())
                        .category(new CategoryDto(product.getCategory().getName()))
                        .build();
                productCachePort.put(id, dto, null);
                return dto;
            } else {
                log.error("Product with id= {} could not be found in the persist store !", id);
                throw new SourceNotFoundException("Product with id= " + id + "could not be found in the persist store !");
            }
        }
        return productDto.get();
    }
}
