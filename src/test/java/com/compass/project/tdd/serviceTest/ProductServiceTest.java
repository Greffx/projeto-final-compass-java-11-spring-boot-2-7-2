package com.compass.project.tdd.serviceTest;

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
import com.compass.project.entity.dto.ProductDto;
import com.compass.project.repository.ProductRepository;
import com.compass.project.service.ProductService;

@SpringBootTest
public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	private Product productForTest() {
		Product justOneProduct = new Product(10L, "Coleira", 250.20, "Coleira para cachorro");
		return justOneProduct;
	}

	private ProductDto productDtoForTest() {
		ProductDto product = new ProductDto(productForTest());
		return product;
	}

	private Optional<Product> optionalProductForTest() {
		Optional<Product> optionalProduct = Optional.of(productForTest());
		return optionalProduct;
	}

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
		ProductDto productDto = productDtoForTest();

		when(productRepository.save(any())).thenReturn(product);
		Product result = productService.insertNewProduct(productDto);

		assertNotNull(result);
		assertEquals(Product.class, result.getClass());
		assertEquals(10L, result.getId());
		assertEquals("Coleira", result.getName());

	}

	@Test
	void endPointUpdateShouldReturnProductWithOtherAtributes() {
		Optional<Product> optionalProduct = optionalProductForTest();
		Product product = productForTest();
		ProductDto productDto = productDtoForTest();

		when(productRepository.save(any())).thenReturn(product);
		when(productRepository.findById(anyLong())).thenReturn(optionalProduct);

		Product result = productService.updateProduct(10L, productDto);

		assertNotNull(result);
		assertEquals(Product.class, result.getClass());
		assertEquals(10L, result.getId());
		assertEquals("Coleira", result.getName());

	}
}