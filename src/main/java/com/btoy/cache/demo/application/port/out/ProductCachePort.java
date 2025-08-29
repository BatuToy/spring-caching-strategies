package com.btoy.cache.demo.application;

import com.btoy.cache.demo.dto.ProductDto;
import jakarta.annotation.Nullable;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

/*
 * @created 29/08/2025 ~~ 12:18
 * author: batu
 */
public interface ProductCachePort {

    Optional<ProductDto> get(String key);

    void put(String key, ProductDto value, @Nullable Duration ttl);

    void evict(String key);

    void mevict(Iterable<String> keys);

    void mput(Iterable<Map.Entry<String, ProductDto>> entries, @Nullable Duration ttl);
}
