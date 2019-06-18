package com.flyway.migration.bb.repository;

import com.flyway.migration.bb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by e068635 on 2/21/2019.
 */
public interface ProductRepository  extends JpaRepository<Product, Long> {

    Product findByid(Long id);

}
