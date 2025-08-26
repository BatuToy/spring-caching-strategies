package com.btoy.cache.demo.dao;

import com.btoy.cache.demo.entity.Category;
import com.btoy.cache.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
 * @created 26/08/2025 ~~ 20:26
 * author: batu
 */
@Repository // provides DbException initial with default transaction management !
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findById(String id);

}
