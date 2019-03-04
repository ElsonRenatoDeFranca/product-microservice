package com.product.pcf.webservice.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.product.pcf.webservice.repository")
@EntityScan("com.product.pcf.webservice.entity")
@ComponentScan({ "com.product.pcf.webservice.config","com.product.pcf.webservice.controller", "com.product.pcf.webservice.service", "com.product.pcf.webservice.impl"})
@EnableCaching
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}

