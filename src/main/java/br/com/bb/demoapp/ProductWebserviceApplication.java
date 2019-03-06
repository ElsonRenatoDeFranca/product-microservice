package br.com.bb.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
@EnableJpaRepositories("br.com.bb.repository")
@EntityScan("br.com.bb.entity")
@ComponentScan({ "br.com.bb.config","br.com.bb.controller", "br.com.bb.service", "br.com.bb.service.impl"})
@EnableCaching
public class ProductWebserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductWebserviceApplication.class, args);
    }

}

