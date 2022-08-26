package com.compass.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.compass.project.entity.Product;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.compass.project")).paths(PathSelectors.any())
				.build().apiInfo(infoAboutApiProduct()).useDefaultResponseMessages(false).ignoredParameterTypes(Product.class);

	}

	private ApiInfo infoAboutApiProduct() {
		return new ApiInfoBuilder().title("API REST Product").version("1.0")
				.description("Final project, API REST to create, read, update and delete products").build();
	}

}
