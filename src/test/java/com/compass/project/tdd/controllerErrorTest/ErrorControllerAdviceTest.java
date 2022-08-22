package com.compass.project.tdd.controllerErrorTest;

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
public class ErrorControllerAdviceTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnBadRequestCode() throws Exception {
		String product = "{\"name\":\"\",\"price\":\"10.20\",\"description\":\"Product_test\"}";

		mockMvc.perform(
				MockMvcRequestBuilders.post(uriContent())
				.content(product)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	void shouldReturnNotFoundCode() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get(uriContentById())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	void shouldReturnInternalErrorCode() throws Exception {
		String product = "{\"name\":\"ProductTest\",\"price\":\"10.20\",\"description\":\"Product_test\"}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.put("/products/a")
				.content(product)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(500));
	}
	
	private URI uriContent() throws Exception {
		URI uri = new URI("/products");
		return uri;
	}
	
	private URI uriContentById() throws Exception {
		URI uri = new URI("/products/12");
		return uri;
	}
}
