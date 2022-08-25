package com.compass.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.compass.project.entity.dto.ProductDto;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Double price;

	private String description;

	public Product() {
	}

	public Product(Long id, String name, Double price, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public Product(ProductDto product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}
}