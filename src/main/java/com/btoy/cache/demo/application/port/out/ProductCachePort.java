package com.btoy.cache.demo.application.port.out;

import com.btoy.cache.demo.application.cacheable_dto.ProductDto;
import jakarta.annotation.Nullable;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * @created 29/08/2025 ~~ 12:18
 * author: batu
 */
public interface ProductCachePort {

    Optional<ProductDto> get(String id);

    void put(String id, ProductDto value, @Nullable Duration ttl);

    void evict(String id);

    void mevict(List<String> idS);

    void mput(List<Map.Entry<String, ProductDto>> entries, @Nullable Duration ttl);
}
