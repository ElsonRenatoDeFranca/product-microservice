package com.product.pcf.webservice.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "PRODUCT")
@Data
public class Product {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PRODUCT_ID", length = 20)
    private Long productId;

    @Column(name = "PRODUCT_NAME", length = 50)
    private String name;

    @Column(name = "PRODUCT_DESCRIPTION", length = 80)
    private String description;

    @Column(name = "PRODUCT_COST", length = 30)
    private String cost;
}
