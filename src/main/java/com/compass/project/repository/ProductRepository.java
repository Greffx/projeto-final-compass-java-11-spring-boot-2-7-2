package com.compass.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.compass.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice AND p.name = :name")
	List<Product> filterByPriceAndName(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice,
			@Param("name") String name);
	
	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
	List<Product> filterByMinMaxPrice(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice")
	List<Product> filterByPriceGreaterThan(@Param("minPrice") Double price);

	@Query("SELECT p FROM Product p WHERE p.price <= :maxPrice")
	List<Product> filterByPriceLessThan(@Param("maxPrice") Double price);

	@Query("SELECT p FROM Product p WHERE p.name >= :name")
	List<Product> filterByName(@Param("name") String name);
}
