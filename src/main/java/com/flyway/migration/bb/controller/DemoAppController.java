package com.flyway.migration.bb.controller;


import com.flyway.migration.bb.entity.Product;
import com.flyway.migration.bb.exception.ProductNotFoundException;
import com.flyway.migration.bb.service.IProductService;
import com.flyway.migration.bb.exception.ProductAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoAppController {

    @Autowired
    private IProductService productService;

    @RequestMapping(method=RequestMethod.POST, value="/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductAlreadyExistsException, ProductNotFoundException {
        Product persistedCategory = productService.createProduct(product);

        if(null != persistedCategory){
            return new ResponseEntity<> (persistedCategory,HttpStatus.OK);
        }else{
            return new ResponseEntity<> (persistedCategory,HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method=RequestMethod.DELETE, value="/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> removeProduct(@PathVariable(name="id") Long id) throws ProductNotFoundException {
        try {
            productService.removeProduct(id);
            return new ResponseEntity<> (HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method=RequestMethod.GET,value="/products")
    public ResponseEntity<List<Product>> findAll(){

        try {
            List<Product> products = productService.findAll();
            return new ResponseEntity<>(products, HttpStatus.OK);

        }catch(ProductNotFoundException prodEx){
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method=RequestMethod.GET,value="/products/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){

        try {
            Product product = productService.findProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);

        }catch(ProductNotFoundException cartEx){
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method=RequestMethod.PUT, value="/products/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id){

        try{
            Product productUpdated = productService.updateProduct(id, product);
            return new ResponseEntity<>(productUpdated, HttpStatus.OK);

        }catch(ProductNotFoundException cartEx){
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }


}
