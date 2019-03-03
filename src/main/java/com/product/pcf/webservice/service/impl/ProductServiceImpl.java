package com.product.pcf.webservice.service.impl;

import com.product.pcf.webservice.constants.ProductConstants;
import com.product.pcf.webservice.entity.Product;
import com.product.pcf.webservice.exception.ProductNotFoundException;
import com.product.pcf.webservice.repository.ProductRepository;
import com.product.pcf.webservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductById(Long productId) throws ProductNotFoundException{
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_ERROR_MESSAGE));
    }

    @Override
    @Cacheable("products")
    public List<Product> findAll()  throws ProductNotFoundException{
        List<Product> products = productRepository.findAll();
        if(products !=null){
            return products;
        }else{
            throw new ProductNotFoundException(ProductConstants.PRODUCT_LIST_IS_EMPTY_ERROR_MESSAGE);
        }
    }

    @Override
    public Product createProduct(Product product) throws ProductNotFoundException {
        Product existingProduct = this.findProductById(product.getId());

        if(existingProduct.getId() == null){
            return this.productRepository.save(product);
        }else{
            throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    @Override
    public Product removeProduct(Long productId) throws ProductNotFoundException {

        Product existingProduct = this.findProductById(productId);

        if(existingProduct.getId() != null){
            this.productRepository.delete(existingProduct);
        }else{
            throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
        }

        return existingProduct;

    }


}
