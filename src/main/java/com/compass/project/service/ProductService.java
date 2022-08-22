package com.compass.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.project.entity.Product;
import com.compass.project.entity.dto.ProductDto;
import com.compass.project.repository.ProductRepository;
 
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getListOfAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		Optional<Product> onlyOneProductById = productRepository.findById(id);
		return onlyOneProductById.get();
	}

	public Product insertNewProduct(ProductDto productDto) {
		Product product = new Product(productDto);
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@SuppressWarnings("deprecation")
	public Product updateProduct(Long id, ProductDto product) {
		
		Product productThatWillBeChanged = productRepository.getById(id);

		if (productThatWillBeChanged != null) {
			productThatWillBeChanged.setDescription(product.getDescription());
			productThatWillBeChanged.setName(product.getName());
			productThatWillBeChanged.setPrice(product.getPrice());
		}
		return productRepository.save(productThatWillBeChanged);
	}
 
	public List<Product> listOfGreaterAndLowerPriceAndNameProduct(Double minPrice, Double maxPrice, String name) {
		if (minPrice == null && maxPrice == null) {
			return productRepository.filterByName(name);
		} else if (minPrice == null && name == null) {
			return productRepository.filterByPriceLessThan(maxPrice);
		} else if (maxPrice == null && name == null) {
			return productRepository.filterByPriceGreaterThan(minPrice);
		} else if (name == null) {
			return productRepository.filterByMinMaxPrice(minPrice, maxPrice);
		} else if (minPrice == null) {
			return productRepository.filterByMaxPriceAndName(maxPrice, name);
		} else if (maxPrice == null) {
			return productRepository.filterByMinPriceAndName(minPrice, name);
		}
		return productRepository.filterByPriceAndName(minPrice, maxPrice, name);
	}
}