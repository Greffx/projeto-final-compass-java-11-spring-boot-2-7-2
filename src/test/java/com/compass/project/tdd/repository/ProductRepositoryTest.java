package com.compass.project.tdd.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.compass.project.entity.Product;

import com.compass.project.repository.ProductRepository;
import com.compass.project.service.ProductService;

@SpringBootTest
public class ProductRepositoryTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private Product product;

	@BeforeEach
	void BeforeEachOneOfThoseTestsDoThisOneMethod() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void methodGetListOfAllProductsShouldReturnAListOfProdcuts() {
		Product product = productForTest();
		Product product1 = productForTest();

		when(productService.getListOfAllProducts()).thenReturn(List.of(product, product1));
		List<Product> result = productService.getListOfAllProducts();

		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	void endPointShouldGetProductByIdUsingMethodFromRepositoryCrud() {
		Optional<Product> product = optionalProductForTest();

		when(productRepository.findById(anyLong())).thenReturn(product);
		Product result = productService.getProductById(10L);

		assertNotNull(result);
		assertEquals(Product.class, result.getClass());
	}

	@Test
	void endPointShouldGetMethodDeleteFromRepositoryAndDeleteAProduct() {
		Optional<Product> product = optionalProductForTest();

		when(productRepository.findById(anyLong())).thenReturn(product);
		doNothing().when(productRepository).deleteById(anyLong());
		productService.deleteProduct(10L);

		verify(productRepository, times(1)).deleteById(anyLong());
	}

	@Test
	void endPointCreateShouldReturnNewProduct() {
		Product product = productForTest();
		
		when(productRepository.save(any())).thenReturn(product);
		Product result = productService.insertNewProduct(product);

		assertNotNull(result);
		assertEquals(Product.class, result.getClass());
		assertEquals(10L, result.getId());
		assertEquals("Coleira", result.getName());

	}
	
	@Test
	void endPointUpdateShouldReturnProductWithOtherAtributes() {
		Optional<Product> optionalProduct = optionalProductForTest();
		Product product = productForTest();
		
		when(productRepository.save(any())).thenReturn(product);
		when(productRepository.findById(anyLong())).thenReturn(optionalProduct);
		
		Product result = productService.updateProduct(10L, product);

		assertNotNull(result);
		assertEquals(Product.class, result.getClass());
		assertEquals(10L, result.getId());
		assertEquals("Coleira", result.getName());

	}

	public Product productForTest() {
		Product justOneProduct = new Product(10L, "Coleira", 250.20, "Coleira para cachorro");
		return justOneProduct;
	}

	public Optional<Product> optionalProductForTest() {
		Optional<Product> optionalProduct = Optional.of(productForTest());
		return optionalProduct;
	}
}