package com.btoy.cache.demo.application.port.in;

import com.btoy.cache.demo.application.cacheable_dto.ProductDto;

/*
 * @created 29/08/2025 ~~ 12:15
 * author: batu
 */
public interface ApplicationService {

    ProductDto getProductById(String id);
}
