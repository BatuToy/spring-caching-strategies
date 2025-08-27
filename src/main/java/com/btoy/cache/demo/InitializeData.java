package com.btoy.cache.demo;

import com.btoy.cache.demo.dao.CategoryRepository;
import com.btoy.cache.demo.dao.ProductRepository;
import com.btoy.cache.demo.entity.Category;
import com.btoy.cache.demo.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class InitializeData {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public InitializeData(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void persistData() {
        Category mobile = Category.builder()
                .name("Mobile")
                .build();

        List<Product> products =  IntStream.range(Number.ONE.getVal(), Number.TEN_THOUSAND.getVal())
                .mapToObj(index -> Product.builder()
                        .name("Product" + index)
                        .sku("Prod" + index)
                        .price(Price.ONE_THOUSAND.getVal())
                        .category(mobile)
                        .build())
                .toList();

        categoryRepository.save(mobile);
        productRepository.saveAllAndFlush(products);
    }

    private enum Price {
        ONE(BigDecimal.ONE),
        TWO(BigDecimal.TWO),
        ZERO(BigDecimal.ZERO),
        ONE_THOUSAND(BigDecimal.valueOf(1000)),
        TEN_THOUSAND(BigDecimal.valueOf(10000));

        private final BigDecimal val;

        Price(BigDecimal val) {
            this.val = val;
        }

        public BigDecimal getVal() {
            return val;
        }
    }

    private enum Number {
        ONE(1),
        TWO(2),
        ZERO(0),
        ONE_THOUSAND(1000),
        TEN_THOUSAND(10000);

        private final Integer val;

        Number(Integer val) {
            this.val = val;
        }

        public Integer getVal() {
            return val;
        }
    }
}
