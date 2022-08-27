package com.compass.project.entity.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import com.compass.project.entity.Product;

public class ProductDto {

	private Long id;

	@NotNull
	@NotEmpty
	@Length(min = 2)
	private String name;

	@NotNull
	@DecimalMin(value = "0.1")
	@Positive
	private Double price;

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String description;

	public ProductDto() {
	}

	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public static Page<ProductDto> convertToDto(Page<Product> products) {
		return products.map(ProductDto::new);
	}
}