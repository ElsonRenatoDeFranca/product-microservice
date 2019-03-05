package com.product.pcf.webservice.controller;

import com.product.pcf.webservice.demoapp.ProductApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@WebAppConfiguration
public class ProductControllerTest {

	@Autowired
    private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
    public void findByProductId() throws Exception {

		mockMvc.perform( MockMvcRequestBuilders
				.get("/product/{productId}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arroz"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Arroz Parboilizado"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.cost").value("3.50"));

	}

	@Test
	public void findAll() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/products"))
				.andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].productId",is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", is("Arroz")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", is("Arroz Parboilizado")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].cost", is("3.50")))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].productId",is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].name", is("Feijao")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].description", is("Feijao Sitio Cercado")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].cost", is("4.10")))




		;
						//.andExpect(jsonPath("$.[0].id",is(1)));



	}
	/*@Test
	public void listByCategoryEletrodomésticos() throws Exception {
		mockMvc.perform(get("/product/listByCategory/2"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].id", is(3)))
		.andExpect(jsonPath("$[0].name", is("Aspirador de pó")))
		.andExpect(jsonPath("$[1].id", is(4)))
		.andExpect(jsonPath("$[1].name", is("Batedeira")))
		.andExpect(jsonPath("$[2].id", is(5)))
		.andExpect(jsonPath("$[2].name", is("Liquidificador")));
	}

	@Test
	public void listByCategoryMóveis() throws Exception {
		mockMvc.perform(get("/product/listByCategory/3"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$[0].id", is(6)))
		.andExpect(jsonPath("$[0].name", is("Sofá")))
		.andExpect(jsonPath("$[1].id", is(7)))
		.andExpect(jsonPath("$[1].name", is("Mesa")))
		.andExpect(jsonPath("$[2].id", is(8)))
		.andExpect(jsonPath("$[2].name", is("Estante")));
	}

	*/
}
