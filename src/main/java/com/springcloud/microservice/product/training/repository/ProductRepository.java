package com.springcloud.microservice.product.training.repository;

import com.springcloud.microservice.product.training.entity.Product;

/**
 * Created by e068635 on 6/18/2019.
 */
public interface ProductRepository {

    Product findProductByid(Long id);
}
