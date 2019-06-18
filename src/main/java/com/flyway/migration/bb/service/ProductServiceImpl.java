package com.flyway.migration.bb.service;

import com.flyway.migration.bb.entity.Product;
import com.flyway.migration.bb.repository.ProductRepository;
import com.flyway.migration.bb.constants.DemoAppConstants;
import com.flyway.migration.bb.exception.ProductAlreadyExistsException;
import com.flyway.migration.bb.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductById(Long id) throws ProductNotFoundException {
        return productRepository.findByid(id);
    }

    @Override
    public List<Product> findAll() throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product createProduct(Product product) throws ProductAlreadyExistsException, ProductNotFoundException {
        Product searchedProduct = this.findProductById(product.getId());

        if(searchedProduct != null){
            throw new ProductNotFoundException(DemoAppConstants.PRODUCT_ALREADY_EXISTS_ERROR_MESSAGE);
        }
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(Long id) throws ProductNotFoundException {
        Product searchedProduct = this.findProductById(id);

        if(searchedProduct == null){
            throw new ProductNotFoundException(DemoAppConstants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
        }
        productRepository.delete(searchedProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {

        Product searchedProduct = this.findProductById(id);

        if(searchedProduct == null){
            throw new ProductNotFoundException(DemoAppConstants.PRODUCT_NOT_FOUND_ERROR_MESSAGE);
        }
        product.setId(id);
        return productRepository.save(product);
    }


}
