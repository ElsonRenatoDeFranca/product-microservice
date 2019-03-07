package br.com.bb.demoapp.controller;

import br.com.bb.demoapp.ProductWebserviceApplication;
import br.com.bb.entity.Product;
import br.com.bb.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductWebserviceApplication.class)
@WebAppConfiguration
public class ProductControllerTest {

	@Autowired
    private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Mock
	private ProductRepository productRepository;

	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
    public void findByProductId() throws Exception {

		mockMvc.perform( MockMvcRequestBuilders
				.get("/products/{id}", 7)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(7))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mesa"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Mesa de Jantar com 4 cadeiras Tabaco-Perola Rossini"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(510.0));

	}

	@Test
	public void findAll() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/products"))
				.andExpect(status().isOk())

				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id",is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", is("Arroz")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", is("Arroz Parboilizado")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].cost", is(3.50)))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].id",is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].name", is("Feijao")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].description", is("Feijao Sitio Cercado")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].cost", is(4.10)))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[2].id",is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[2].name", is("Aspirador de pó")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[2].description", is("Aspirador Vertical Philco Turbo - 309")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[2].cost", is(193.5)))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[3].id",is(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[3].name", is("Batedeira")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[3].description", is("Batedeira Stand Mixer")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[3].cost", is(3.599)))


				.andExpect(MockMvcResultMatchers.jsonPath("$.[4].id",is(5)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[4].name", is("Liquificador")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[4].description", is("Liquidificador Phillips Walita")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[4].cost", is(149.0)))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[5].id",is(6)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[5].name", is("Sofá")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[5].description", is("Sofá 3 Lugares Retratil Kennedy")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[5].cost", is(2.5)))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[6].id",is(7)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[6].name", is("Mesa")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[6].description", is("Mesa de Jantar com 4 cadeiras Tabaco-Perola Rossini")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[6].cost", is(510.0)))

				.andExpect(MockMvcResultMatchers.jsonPath("$.[7].id",is(8)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[7].name", is("Estante")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[7].description", is("Estante Multiuso 5 Divisórias")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[7].cost", is(420.0)));

	}



}
