package com.btoy.cache.demo.dao;

import com.btoy.cache.demo.entity.Category;
import jakarta.persistence.NamedEntityGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * @created 27/08/2025 ~~ 01:28
 * author: batu
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("SELECT c FROM category AS c")
    @EntityGraph(value = "Category.products", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Category> findAllCategoriesWithProducts();

    Optional<Category> findById(String id);

    /**
     * type = EntityGraph.EntityGraphType.LOAD vs type = EntityGraph.EntityGraphType.EAGER
     * Main diff is other fields fetching is changing.
     * -- Eager is eagerly fetch the attribute node other's stay LAZY
     * -- Load is eagerly fetch the attribute node other's stay original !
     */

}
