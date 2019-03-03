package com.product.pcf.webservice.demoapp;

import com.product.pcf.webservice.entity.Product;
import com.product.pcf.webservice.exception.ProductNotFoundException;
import com.product.pcf.webservice.repository.ProductRepository;
import com.product.pcf.webservice.service.IProductService;
import com.product.pcf.webservice.service.impl.ProductServiceImpl;
import org.hamcrest.Matchers;
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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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


    private Product createProduct(Long id, String name, String description, Double cost){
        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setCost(cost);

        return newProduct;

    }
    @Test
    public void createProduct_shouldReturnNewCart_whenCreateProductServiceIsInvoked() throws ProductNotFoundException{

        //Given
        Product newProduct = createProduct(1L,"Aspirador de pó","Aspirador",20.00);

        //When
        when(productRepository.findById(eq(1L))).thenReturn(Optional.of(newProduct));
        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        Product product = productService.createProduct(newProduct);

        verify(productRepository, times(1)).save(newProduct);

        //Then
        assertThat(product, is(notNullValue()));
    }

    @Test
    public void findProductById_shouldReturnProductNotFoundException_whenProductIdIsNotFound() throws ProductNotFoundException {

        //Given
        Product newProduct = createProduct(5L,"Tenis Olympicus","Tenis Branco",560.00);

        //When
        when(productRepository.findById(eq(10L))).thenReturn(Optional.of(newProduct));
        thrown.expect(ProductNotFoundException.class);

        Product productSearched = productService.findProductById(5L);

        //Then
        assertThat(productSearched, is(nullValue()));
    }

    @Test
    public void findProductById_shouldReturnProduct_whenProductWasFound() throws ProductNotFoundException {

        //Given
        Product newProduct = createProduct(2L,"Feijao Sitio Cercado","Feijao Branco",5.00);
        Long expectedId = 2L;

        //When
        when(productRepository.findById(eq(expectedId))).thenReturn(Optional.of(newProduct));
        Product productSearched = productService.findProductById(2L);

        verify(productRepository, times(1)).findById(eq(expectedId));

        //Then
        assertThat(productSearched, is(notNullValue()));
        assertThat(productSearched, hasProperty("id", is(equalTo(2L))));
        assertThat(productSearched, hasProperty("name", is(equalTo("Feijao Sitio Cercado"))));
        assertThat(productSearched, hasProperty("description", is(equalTo("Feijao Branco"))));
        assertThat(productSearched, hasProperty("cost", is(equalTo(5.00))));
    }

    @Test
    public void removeProduct_shouldReturnListWithoutRemovedProduct_whenProductWasRemoved()throws ProductNotFoundException {

        //Given
        Product newProduct = createProduct(2L,"Feijao Sitio Cercado","Feijao Branco",5.00);
        Long expectedId = 2L;

        //When
        when(productRepository.findById(eq(expectedId))).thenReturn(Optional.of(newProduct));
        //when(productRepository.findById(eq(expectedId))).thenReturn(Optional.of(newProduct));


        //Then
        Product product = productService.removeProduct(newProduct.getId());
        //verify(productRepository, times(3)).deleteById(eq(expectedId));


        assertThat(product, hasProperty("name", is(nullValue())));
    }

    @Test
    public void removeProduct_shouldReturnListWithAllProducts_whenFindAllServiceIsInvoked()throws ProductNotFoundException {

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