package com.compass.project.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/products")
@Api("API REST Product")
@CrossOrigin(origins = "http://localhost:9999/swagger-ui.html#/swaggerDoc")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	@ApiOperation("Return a list of all products")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return a list of products filtered by search parameters"),
			@ApiResponse(code = 400, message = "Return URL, message and code error"),
			@ApiResponse(code = 404, message = "Return URL, message and code error"),
			@ApiResponse(code = 500, message = "Return URL, message and code error"), })
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Which page will load", defaultValue = "0"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantity of products that will appear", defaultValue = "5"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sort by name or description or price") })
	public ResponseEntity<Page<ProductDto>> listOfAllProducts(
			@ApiIgnore @PageableDefault(page = 0, size = 5) Pageable pageable) {
		return ResponseEntity.ok().body(ProductDto.convertToDto(productService.getListOfAllProducts(pageable)));
	}

	@GetMapping("/search")
	@ApiOperation("Return a list of products filtered by parameters")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return a list of products filtered by search parameters"),
			@ApiResponse(code = 400, message = "Return URL, message and code error"),
			@ApiResponse(code = 404, message = "Return URL, message and code error"),
			@ApiResponse(code = 500, message = "Return URL, message and code error"), })
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Which page will load", defaultValue = "0"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantity of products that will appear", defaultValue = "5"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sort by name or description or price") })
	public ResponseEntity<Page<ProductDto>> listWithParam(
			@RequestParam(name = "minPrice", required = false) Double minPrice,
			@RequestParam(name = "maxPrice", required = false) Double maxPrice,
			@RequestParam(name = "product", required = false) String name,
			@PageableDefault(page = 0, size = 5) @ApiIgnore Pageable pageable) {
		return ResponseEntity.ok().body(ProductDto.convertToDto(
				productService.listOfGreaterAndLowerPriceAndNameProduct(minPrice, maxPrice, name, pageable)));
	}

	@GetMapping("/{id}")
	@ApiOperation("Return only one product by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return a product"),
			@ApiResponse(code = 400, message = "Return URL, message and code error"),
			@ApiResponse(code = 404, message = "Return URL, message and code error"),
			@ApiResponse(code = 500, message = "Return URL, message and code error"), })
	public ResponseEntity<ProductDto> onlyOneProductById(@PathVariable Long id) {
		return ResponseEntity.ok().body(new ProductDto(productService.getProductById(id)));
	}

	@PostMapping
	@ApiOperation("Create a new product")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Return a product"),
			@ApiResponse(code = 400, message = "Return field, message and code error"),
			@ApiResponse(code = 404, message = "Return URL, message and code error"),
			@ApiResponse(code = 500, message = "Return URL, message and code error"), })
	public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productService.insertNewProduct(productDto)).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(productService.insertNewProduct(productDto)));
	}

	@PutMapping("/{id}")
	@ApiOperation("Update a product by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return a updated product"),
			@ApiResponse(code = 400, message = "Return field, message and code error"),
			@ApiResponse(code = 404, message = "Return URL, message and code error"),
			@ApiResponse(code = 500, message = "Return URL, message and code error"), })
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
		return ResponseEntity.ok().body(new ProductDto(productService.updateProduct(id, productDto)));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Delete a product by id")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Return status but with no content"),
			@ApiResponse(code = 400, message = "Return URL, message and code error"),
			@ApiResponse(code = 404, message = "Return URL, message and code error"),
			@ApiResponse(code = 500, message = "Return URL, message and code error"), })
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
