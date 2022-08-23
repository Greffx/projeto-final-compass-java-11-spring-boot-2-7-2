package com.compass.project.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compass.project.entity.Product;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.compass.project")).paths(PathSelectors.ant("/**"))
				.build().ignoredParameterTypes(Product.class)
				.globalResponseMessage(RequestMethod.GET, responseMessageGetAndPutMethod())
				.globalResponseMessage(RequestMethod.POST, responseMessagePostMethod())
				.globalResponseMessage(RequestMethod.PUT, responseMessageGetAndPutMethod())
				.globalResponseMessage(RequestMethod.DELETE, responseMessageDeletetMethod());
	}

	private List<ResponseMessage> responseMessageGetAndPutMethod() {

		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

			{
				add(new ResponseMessageBuilder().code(500).message("Internal Server Error")
						.responseModel(new ModelRef("Error")).build());
				add(new ResponseMessageBuilder().code(404).message("Not Found").build());
				add(new ResponseMessageBuilder().code(400).message("Bad Request").build());
			}
		};
	}
	
	private List<ResponseMessage> responseMessagePostMethod() {

		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

			{
				add(new ResponseMessageBuilder().code(500).message("Internal Server Error")
						.responseModel(new ModelRef("ResponseStatusAndMessage")).build());
				add(new ResponseMessageBuilder().code(404).message("Not Found").build());
				add(new ResponseMessageBuilder().code(201).message("Created").build());
				add(new ResponseMessageBuilder().code(400).message("Bad Request").build());
			}
		};
	}
	
	private List<ResponseMessage> responseMessageDeletetMethod() {

		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

			{
				add(new ResponseMessageBuilder().code(500).message("Internal Server Error")
						.responseModel(new ModelRef("ResponseStatusAndMessage")).build());
				add(new ResponseMessageBuilder().code(404).message("Not Found").build());
				add(new ResponseMessageBuilder().code(204).message("No Content").build());
				add(new ResponseMessageBuilder().code(400).message("Bad Request").build());
			}
		};
	}
}
