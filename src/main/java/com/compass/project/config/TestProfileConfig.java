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
		Product product6 = new Product(null, "Óculos Vr", 410.00, "Para celular");
		Product product7 = new Product(null, "Tênis de Corrida", 100.10, "Tênis de esportista");
		Product product8 = new Product(null, "Camiseta de time", 90.70, "Original");
		Product product9 = new Product(null, "Carregador Portátil", 80.59, "Carrega até 5 celulares");
		Product product10 = new Product(null, "Chinelo", 20.90, "Número 41/42");
		Product product11 = new Product(null, "Copo de Vidro", 15.00, "2 unidades");
		Product product12 = new Product(null, "Copo Plástico", 5.10, "5 unidades");

		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11, product12));
	}
}
