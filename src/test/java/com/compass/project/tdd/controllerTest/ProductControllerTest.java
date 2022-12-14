package com.compass.project.tdd.controllerTest;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private URI uriContent() throws Exception {
		URI uri = new URI("/products");
		return uri;
	}

	private URI uriContentById() throws Exception {
		URI uri = new URI("/products/1");
		return uri;
	}

	private String productTest() {
		String product = "{\"name\":\"ProductTest\",\"price\":\"10.20\",\"description\":\"Product_test\"}";
		return product;
	}

	@Test
	void shouldReturnStatusCreated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(uriContent()).content(productTest())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	void shouldReturnNoContent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(uriContentById()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(204));
	}

	@Test
	void shouldReturnOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(uriContent()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void shouldByIdReturnOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(uriContentById()).content(productTest())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void whenUpdateAProductByIdReturnOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put(uriContentById()).content(productTest())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200));
	}
}
