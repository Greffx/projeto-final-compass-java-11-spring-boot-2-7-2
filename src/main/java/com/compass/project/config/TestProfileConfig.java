package com.compass.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.compass.project.entity.Product;
import com.compass.project.repository.ProductRepository;

@Configuration
@Profile("test")
public class TestProfileConfig {

	@Autowired
	private ProductRepository productRepository;

	@Bean
	public void dbInformation() {

		Product product1 = new Product(null, "Monitor", 210.50, "Descrição do meu primeiro produto da história");
		Product product2 = new Product(null, "PC Gamer", 12250.10, "Descrição do meu segundo produto da história");
		Product product3 = new Product(null, "Vídeo Game", 3000.00, "Descrição do meu terceiro produto da história");
		Product product4 = new Product(null, "The Last Of Us II", 300.00, "Descrição do meu quarto produto da história");

		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));
	}
}
