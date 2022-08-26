package com.compass.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compass.project.entity.Product;
import com.compass.project.entity.dto.ProductDto;
import com.compass.project.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Page<Product> getListOfAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
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
		return productRepository.listOfGreaterAndLowerPriceAndNameProduct(minPrice, maxPrice, name);
	}
}