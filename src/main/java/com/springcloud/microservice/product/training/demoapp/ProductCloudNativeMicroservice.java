package com.springcloud.microservice.product.training.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
@EntityScan("com.hotelreservation.microservices.entity")
@EnableJpaRepositories("com.hotelreservation.microservices.repository")
@ComponentScan({ "com.hotelreservation.microservices.common","com.hotelreservation.microservices.converter","com.hotelreservation.microservices.controller", "com.hotelreservation.microservices.service", "com.hotelreservation.microservices.service.impl"})
@EnableCaching
@EnableSwagger2
public class ProductCloudNativeMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(HotelRoomCloudNativeWebserviceApplication.class, args);
	}
}
