package com.product.pcf.webservice.vo;

import com.product.pcf.webservice.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by e068635 on 2/20/2019.
 */
@Data
@EqualsAndHashCode
public class CartVO implements Serializable{

    private Long id;

    private List<Product> products;

}
