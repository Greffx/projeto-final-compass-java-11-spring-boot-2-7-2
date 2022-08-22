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

		Product product1 = new Product(null, "Monitor", 210.50, "Monitor 42 polegadas");
		Product product2 = new Product(null, "PC Gamer", 12250.10, "Pc gamer para gamers");
		Product product3 = new Product(null, "Vídeo Game", 3000.00, "Console da atualidade");
		Product product4 = new Product(null, "The Last Of Us II", 300.00, "Descrição do meu quarto produto da história");
		Product product5 = new Product(null, "Cadeira Gamer", 500.00, "Uma cadeira gamer de qualidade");
		Product product6 = new Product(null, "Gabinete", 110.30, "Gabinete gamer");

		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6));
	}
}
