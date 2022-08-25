package com.compass.project.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compass.project.entity.dto.ProductDto;
import com.compass.project.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDto>> listOfAllProducts() {
		return ResponseEntity.ok().body(ProductDto.convertToDto(productService.getListOfAllProducts()));
	}

//	@GetMapping("/search")
//	public ResponseEntity<List<ProductDto>> listWithParam(
//			@RequestParam(name = "minPrice", required = false) Double minPrice,
//			@RequestParam(name = "maxPrice", required = false) Double maxPrice,
//			@RequestParam(name = "product", required = false) String name) {
//		return ResponseEntity.ok().body(ProductDto
//				.convertToDto(productService.listOfGreaterAndLowerPriceAndNameProduct(minPrice, maxPrice, name)));
//	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> onlyOneProductById(@PathVariable  Long id) {
		return ResponseEntity.ok().body(new ProductDto(productService.getProductById(id)));
	}

	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productService.insertNewProduct(productDto))
				.toUri();
		return ResponseEntity.created(uri).body(new ProductDto(productService.insertNewProduct(productDto)));
	}
 
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable  Long id, @RequestBody @Valid ProductDto productDto) {
		return ResponseEntity.ok().body(new ProductDto(productService.updateProduct(id, productDto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable  Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
