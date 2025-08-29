package com.btoy.cache.demo.infra;

import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

/*
 * @created 29/08/2025 ~~ 09:29
 * author: batu
 */
public interface CachePort<K, V extends Serializable> {

    Optional<V> get(K key);

    void put(K key, V value, @Nullable Duration ttl);

    void evict(K key);

    void mevict(Iterable<K> keys);

    void mput(Iterable<Map.Entry<K, V>> entries, @Nullable Duration ttl);
}
