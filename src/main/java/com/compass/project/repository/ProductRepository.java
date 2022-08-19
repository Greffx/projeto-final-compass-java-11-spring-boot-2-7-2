package com.compass.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.compass.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice AND lower(p.name) like lower(concat('%', :name,'%'))")
	List<Product> filterByPriceAndName(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice,
			@Param("name") String name);
	
	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
	List<Product> filterByMinMaxPrice(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice")
	List<Product> filterByPriceGreaterThan(@Param("minPrice") Double price);

	@Query("SELECT p FROM Product p WHERE p.price <= :maxPrice")
	List<Product> filterByPriceLessThan(@Param("maxPrice") Double price);

	@Query("SELECT p FROM Product p WHERE lower(p.name) like lower(concat('%', :name,'%'))")
	List<Product> filterByName(@Param("name") String name);
	
	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND lower(p.name) like lower(concat('%', :name,'%'))")
	List<Product> filterByMinPriceAndName(@Param("minPrice") Double minPrice, @Param("name") String name);
	
	@Query("SELECT p FROM Product p WHERE p.price <= :maxPrice AND lower(p.name) like lower(concat('%', :name,'%'))")
	List<Product> filterByMaxPriceAndName(@Param("maxPrice") Double maxPrice, @Param("name") String name);
}
