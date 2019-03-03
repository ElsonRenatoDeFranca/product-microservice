package com.product.pcf.webservice.demoapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;



/**
 * Created by e068635 on 2/8/2019.
 */
@RunWith(SpringRunner.class)
public class CartServiceControllerTest {


    @Test
    public void retrieveCartById_shouldReturn200_whenCartIsFound() {

        given().
                pathParam("cartId", "1").
                when().
                get("/carts/{cartId}").
                then().
                statusCode(200);

    }

    @Test
    public void retrieveCartById_shouldReturn400_whenCartIdIsNotFound() {

        given().
                pathParam("cartId", "10").
                when().
                get("/carts/{cartId}").
                then().
                statusCode(400);

    }


}
