package com.product.pcf.webservice.service;

import com.product.pcf.webservice.entity.Product;
import com.product.pcf.webservice.exception.ProductAlreadyExistsException;
import com.product.pcf.webservice.exception.ProductNotFoundException;

import java.util.List;

public interface IProductService {

    /**
     *
     * @param productId
     * @return
     */
    Product findProductById(Long productId) throws ProductNotFoundException;

    /**
     *
     * @return
     */
    List<Product> findAll() throws ProductNotFoundException;


    /**
     *
     * @param product
     * @return
     */
    Product createProduct(Product product)  throws ProductAlreadyExistsException, ProductNotFoundException;

    /**
     *
     * @param productId
     * @return
     * @throws ProductNotFoundException
     */
    void removeProduct(Long productId) throws ProductNotFoundException;

}
