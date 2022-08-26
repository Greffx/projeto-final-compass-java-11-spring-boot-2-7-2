package com.compass.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.compass.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select p from Product p where (:minPrice is null or p.price >= :minPrice) and (:maxPrice is null or p.price <= :maxPrice) and (:name is null or lower(p.name) like lower(concat('%', :name, '%')))")
	Page<Product> listOfGreaterAndLowerPriceAndNameProduct(@Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice, @Param("name") String name, Pageable pageable);
}
