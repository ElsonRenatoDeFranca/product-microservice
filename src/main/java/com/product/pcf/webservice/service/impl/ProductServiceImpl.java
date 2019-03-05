package com.product.pcf.webservice.service.impl;

import com.product.pcf.webservice.constants.ProductConstants;
import com.product.pcf.webservice.entity.Product;
import com.product.pcf.webservice.exception.ProductAlreadyExistsException;
import com.product.pcf.webservice.exception.ProductNotFoundException;
import com.product.pcf.webservice.repository.ProductRepository;
import com.product.pcf.webservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductById(Long productId) throws ProductNotFoundException{
        Product product = productRepository.findOne(productId);
        if(product == null){
            throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
        }
        return product;
    }

    @Override
    @Cacheable("products")
    public List<Product> findAll()  throws ProductNotFoundException{
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product createProduct(Product product) throws ProductAlreadyExistsException, ProductNotFoundException {
        Product existingProduct = this.findProductById(product.getProductId());

        if(existingProduct == null){
            return this.productRepository.save(product);
        }else{
            throw new ProductAlreadyExistsException(ProductConstants.PRODUCT_ALREADY_EXISTS_ERROR_MESSAGE);
        }

    }

    @Override
    public Product removeProduct(Long productId) throws ProductNotFoundException {

        Product existingProduct = this.findProductById(productId);

        if(existingProduct != null){
            this.productRepository.delete(existingProduct);
        }else{
            throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
        }

        return existingProduct;

    }


}
