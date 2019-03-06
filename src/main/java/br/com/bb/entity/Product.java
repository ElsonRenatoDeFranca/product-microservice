package br.com.bb.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PRODUCT")
@Data
public class Product {

    @Id
    @Column(name = "PRODUCT_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;

    @Column(name = "ID", length = 20)
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 50)
    private String name;

    @Column(name = "PRODUCT_DESCRIPTION", length = 80)
    private String description;

    @Column(name = "PRODUCT_COST", length = 30)
    private Double cost;

}
