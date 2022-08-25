package com.compass.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compass.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
