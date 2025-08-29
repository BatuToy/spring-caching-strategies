package com.btoy.cache.demo.cache.adapter;

import com.btoy.cache.demo.application.port.out.ProductCachePort;
import com.btoy.cache.demo.cache.KeyGenerator;
import com.btoy.cache.demo.application.cacheable_dto.ProductDto;
import com.btoy.cache.demo.cache.port.CachePort;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * @created 29/08/2025 ~~ 12:16
 * author: batu
 */
@Component
public class ProductCacheAdapter implements ProductCachePort {

    private final CachePort<String, ProductDto> cachePort;

    private static final String PRODUCT_NS = "product";

    public ProductCacheAdapter(CachePort<String, ProductDto> cachePort) {
        this.cachePort = cachePort;
    }

    @Override
    public Optional<ProductDto> get(String id) {
        return cachePort.get(generateKey(id));
    }

    @Override
    public void put(String id, ProductDto value, @Nullable Duration ttl) {
        cachePort.put(generateKey(id), value, ttl);
    }

    @Override
    public void evict(String id) {
        cachePort.evict(generateKey(id));
    }

    @Override
    public void mevict(List<String> ids) {
        cachePort.mevict(ids.stream().map(this::generateKey).toList());
    }

    @Override
    public void mput(List<Map.Entry<String, ProductDto>> entries, @Nullable Duration ttl) {
        cachePort.mput(entries, ttl);
    }

    private String generateKey(String id) {
        return KeyGenerator.generateKey(PRODUCT_NS, id);
    }
}
