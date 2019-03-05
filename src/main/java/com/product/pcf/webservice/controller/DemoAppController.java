package com.product.pcf.webservice.controller;


import com.product.pcf.webservice.entity.Product;
import com.product.pcf.webservice.exception.ProductAlreadyExistsException;
import com.product.pcf.webservice.exception.ProductNotFoundException;
import com.product.pcf.webservice.service.IProductService;
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

    @RequestMapping(method=RequestMethod.GET,value="/product/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable Long productId){

        try {
            Product product = productService.findProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);

        }catch(ProductNotFoundException cartEx){
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method=RequestMethod.GET,value="/products")
    public ResponseEntity<List<Product>> findAll(){

        try {
            List<Product> product = productService.findAll();
            return new ResponseEntity<>(product, HttpStatus.OK);

        }catch(ProductNotFoundException cartEx){
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method=RequestMethod.POST, value="/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return new ResponseEntity<> (createdProduct,HttpStatus.OK);
        } catch (ProductNotFoundException  | ProductAlreadyExistsException e) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method=RequestMethod.DELETE, value="/products/{productId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> removeProduct(@PathVariable(name="productId") Long productId) {

        try {
            productService.removeProduct(productId);
            return new ResponseEntity<> (HttpStatus.OK);
        } catch (ProductNotFoundException prodEx) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
