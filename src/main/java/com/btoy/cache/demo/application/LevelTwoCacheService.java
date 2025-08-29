package com.btoy.cache.demo.application;

import com.btoy.cache.demo.application.mapper.ProductCacheMapper;
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
@Service("level2")
@RequiredArgsConstructor
@Slf4j
public class LevelTwoCacheService implements ApplicationService {

    private final ProductCachePort productCachePort;
    private final ProductRepository productRepository;

    @Override
    public ProductDto getProductById(String id) {
        Optional<ProductDto> productDto = productCachePort.get(id);
        if (productDto.isEmpty()) {
            Optional<Product> optProduct = productRepository.findById(id);
            if (optProduct.isPresent()) {
                ProductDto dto = ProductCacheMapper.toDto(optProduct.get());
                productCachePort.put(id, dto, null);
                return dto;
            } else {
                log.error("Product with id= {} could not be found in the persist store !", id);
                throw new SourceNotFoundException("Product with id= " + id + "could not be found in the persist store !");
            }
        }
        // todo -> LinkedHashmap -> mapping problem ?
        return productDto.get();
    }
}
