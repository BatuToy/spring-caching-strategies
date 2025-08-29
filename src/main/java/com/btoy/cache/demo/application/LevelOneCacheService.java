package com.btoy.cache.demo.application;

import com.btoy.cache.demo.application.port.in.ApplicationService;
import com.btoy.cache.demo.dto.ProductResponseDto;
import com.btoy.cache.demo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

/*
 * @created 26/08/2025 ~~ 20:25
 * author: batu
 */
@Service
@RequiredArgsConstructor
public class CaffeineCacheServiceImpl  { // L1 Caffeine Cache service !!!

    private static final Logger log = Logger.getLogger(CaffeineCacheServiceImpl.class.getSimpleName());

    @PersistenceContext
    private final EntityManager entityManager;

    // Hibernate session is critical in here -> if session closes cache clears itself right away!
    @Transactional(readOnly = true)
        @Cacheable(
                cacheManager = "caffeineCacheManager",
                cacheNames = "product",
                key = "#id",
                unless = "#result == null")
        public ProductResponseDto retrieveProductById(String id) {
            // Check for L1 -> miss -> Check For DB.
            // Check for L1 -> + hits -> returns product's instance.
            Product product1 = entityManager.find(Product.class, id);
            // Already in the L1 -> hits directly.
            Product product2 = entityManager.find(Product.class, id);
            if(product1 == product2) {
                log.info("Product1's and Product2's instances are the same!");
            } else {
                log.info("Product1's and Product2's instances are the different!");
            }
            return new ProductResponseDto(product2.getName(), product2.getSku());
    }
}
