package com.compass.project.tdd.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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

	private List<Product> listOfProducts() {

		List<Product> products = new ArrayList<>();

		Product product1 = new Product(15L, "Coleira", 10.20, "Coleira para cachorro");
		Product product2 = new Product(16L, "Panela", 250.00, "Panela de aço");
		Product product3 = new Product(17L, "Cadeira de Couro", 450.50, "Cadeira de patrão, super confortável");
		products.add(product1);
		products.add(product2);
		products.add(product3);

		return products;
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
	void methodlistOfGreaterAndLowerPriceAndNameProductShouldReturnAListOfProdcuts() {
		PageRequest pageable = PageRequest.of(0, 2);//which page he is and how many products he wants
		Page<Product> pageWithProduct = new PageImpl<>(listOfProducts(), pageable, listOfProducts().size());
		when(productService.listOfGreaterAndLowerPriceAndNameProduct(any(), any(), any(), any())).thenReturn(pageWithProduct);
		
		assertNotNull(pageWithProduct);
		assertEquals(2, pageWithProduct.getSize());
		assertEquals(0, pageable.getPageNumber());
		assertEquals(2, pageWithProduct.getTotalPages());
	}
	
	@Test
	void methodlistOfGreaterAndLowerPriceAndNameProductShouldReturnAListOfProdcutsWithOnlyOneParam() {
		PageRequest pageable = PageRequest.of(0, 1);//which page he is and how many products he wants
		Page<Product> pageWithProduct = new PageImpl<>(listOfProducts(), pageable, listOfProducts().size());
		when(productService.listOfGreaterAndLowerPriceAndNameProduct(400.0, 500.0, "", pageable)).thenReturn(pageWithProduct);
		
		assertNotNull(pageWithProduct);
		assertEquals(1, pageWithProduct.getSize());
		assertEquals(0, pageable.getPageNumber());
		assertEquals(PageImpl.class, pageWithProduct.getClass());
	}

	@Test
	void methodGetListOfAllProductsShouldReturnAListOfProdcuts() {
		PageRequest pageable = PageRequest.of(0, 5);
		Page<Product> pageWithProduct = new PageImpl<>(listOfProducts(), pageable, listOfProducts().size());

		when(productService.getListOfAllProducts(pageable)).thenReturn(pageWithProduct);

		assertNotNull(pageWithProduct);
		assertEquals(3, listOfProducts().size());
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