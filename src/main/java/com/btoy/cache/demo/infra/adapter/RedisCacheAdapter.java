package com.btoy.cache.demo.infra.cache.adapter;

import com.btoy.cache.demo.port.CachePort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/*
 * @created 29/08/2025 ~~ 09:30
 * author: batu
 */
public class RedisCacheAdapter<K, V extends Serializable> implements CachePort<K, V> {

    private final RedisTemplate<K, V> template;
    private final ValueOperations<K, V> ops;

    public RedisCacheAdapter(RedisTemplate<K, V> template) {
        this.template = template;
        this.ops = template.opsForValue();
    }

    @Override
    public Optional<V> get(K key) {
        return Optional.ofNullable(ops.get(key));
    }

    @Override
    public void put(K key, V value, Duration ttl) {
        if(Objects.nonNull(ttl)) {
            ops.set(key, value, ttl);
        }
        ops.set(key, value);
    }

    @Override
    public void evict(K key) {
        template.delete(key);
    }

    @Override
    public void mevict(Iterable<K> keys) {
        keys.forEach(this::evict);
    }

    @Override
    public void mput(Iterable<Map.Entry<K, V>> entries, Duration ttl) {
        entries.forEach(entry -> this.put(entry.getKey(), entry.getValue(), ttl));
    }
}
