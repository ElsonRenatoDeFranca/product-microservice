package br.com.bb.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by e068635 on 2/20/2019.
 */
@Data
@EqualsAndHashCode
public class ProductVO {

    private Long productId;
    private Long id;
    private String name;
    private String description;
    private Double cost;

}
