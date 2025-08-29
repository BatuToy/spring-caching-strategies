package com.btoy.cache.demo.cache;

import com.btoy.cache.demo.entity.Product;
import com.btoy.cache.demo.port.CachePort;
import org.springframework.stereotype.Component;

/*
 * @created 29/08/2025 ~~ 12:16
 * author: batu
 */
@Component
public class ProductCacheAdapter {

    private final CachePort<String , Product> cachePort;

    public ProductCacheAdapter(CachePort<String, Product> cachePort) {
        this.cachePort = cachePort;
    }






}
