package com.product.pcf.webservice.demoapp;

import com.product.pcf.webservice.entity.Product;
import com.product.pcf.webservice.exception.ProductAlreadyExistsException;
import com.product.pcf.webservice.exception.ProductNotFoundException;
import com.product.pcf.webservice.repository.ProductRepository;
import com.product.pcf.webservice.service.IProductService;
import com.product.pcf.webservice.service.impl.ProductServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private IProductService productService = new ProductServiceImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void createProduct_shouldReturnProduct_whenCreateProductIsInvokedSuccessfully() throws ProductNotFoundException, ProductAlreadyExistsException {

        //Given
        Product newProduct = createProduct(1L,"Aspirador de pó","Aspirador",20.00);

        //When
        when(productRepository.findOne(eq(1L))).thenReturn(newProduct);
        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        Product product = productService.createProduct(newProduct);

        //Then
        verify(productRepository, times(1)).save(newProduct);

        assertThat(product, is(notNullValue()));
        assertThat(product, hasProperty("productId",is(equalTo(1L))));
        assertThat(product, hasProperty("name",is(equalTo("Aspirador de pó"))));
        assertThat(product, hasProperty("description",is(equalTo("Aspirador"))));
        assertThat(product, hasProperty("cost",is(equalTo(20.00))));

    }


    @Test
    public void findProductById_shouldReturnProduct_whenProductIdIsFound() throws ProductNotFoundException, ProductAlreadyExistsException {

        //Given
        Product newProduct = createProduct(2L,"Tenis Olympicus","Tenis de corrida",560.00);

        //When
        when(productRepository.findOne(eq(newProduct.getProductId()))).thenReturn(newProduct);
        Product productSearched = productService.findProductById(newProduct.getProductId());

        //Then
        assertThat(productSearched, is(notNullValue()));
        assertThat(productSearched, hasProperty("productId",is(equalTo(2L))));
        assertThat(productSearched, hasProperty("name",is(equalTo("Tenis Olympicus"))));
        assertThat(productSearched, hasProperty("description",is(equalTo("Tenis de corrida"))));
        assertThat(productSearched, hasProperty("cost",is(equalTo(560.00))));

    }

    @Test
    public void removeProduct_shouldRemoveProduct_whenRemoveServiceIsInvoked()throws ProductNotFoundException {

        //Given
        Product newProduct = createProduct(2L, "Feijao Sitio Cercado", "Feijao Branco", 5.00);
        Long expectedId = 2L;

        //When
        when(productRepository.findOne(eq(expectedId))).thenReturn(newProduct);
        productService.removeProduct(newProduct.getProductId());

        //Then
        verify(productRepository, times(1)).delete(newProduct);
    }


    @Test
    public void findAll_shouldReturnListWithAllProducts_whenFindAllServiceIsInvoked()throws ProductNotFoundException {

        //Given
        List<Product> productsMock = createProductList();

        //When
        when(productRepository.findAll()).thenReturn(productsMock);

        List<Product> productsFromService = productService.findAll();

        //Then
        assertThat(productsFromService, hasSize(4));
    }




    private Product createProduct(Long produtId, String name, String description, Double cost){
        Product newProduct = new Product();
        newProduct.setProductId(produtId);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setCost(cost);

        return newProduct;

    }

    private List<Product> createProductList(){

        Product caixaSabaoEmPo = createProduct(10L,"Sabao em pó","Sabao e pó",10.00);
        Product feijao = createProduct(2L,"Feijao Sitio Cercado","Feijao Branco",5.00);
        Product caixaDeLapis = createProduct(3L,"caixa de lapis - Faber Castell","Lapis de cor - 12 Lapis",4.00);
        Product aspiradorDePo = createProduct(4L,"Aspirador de pó - Electrolux","Aspirador de pó",4.00);

        List<Product> products = new ArrayList<>();
        products.add(caixaSabaoEmPo);
        products.add(feijao);
        products.add(caixaDeLapis);
        products.add(aspiradorDePo);

        return products;

    }


}