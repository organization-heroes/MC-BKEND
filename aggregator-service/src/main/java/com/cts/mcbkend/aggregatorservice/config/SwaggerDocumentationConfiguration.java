package com.cts.mcbkend.aggregatorservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cts.mcbkend.aggregatorservice.util.Constants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
/**
 * 
 * @author Sukanta
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfiguration {

	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.enable(swaggerEnabled).select()
				.apis(RequestHandlerSelectors.basePackage(Constants.aggreServ))
				.paths(PathSelectors.any()).build().pathMapping("/");
	}
	@Bean
	public Docket swaggerDocumentApi10() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("aggregator-api-1.0")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage(Constants.aggreServ))
	            .paths(regex("/aggregator/v1.0.*"))
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("1.0").title(Constants.documentApi).description("Documentation Document API v1.0").build());
	}
	@Bean
	public Docket swaggerDocumentApi11() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("aggregator-api-1.1")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage(Constants.aggreServ))
	            .paths(regex("/aggregator/v1.1.*"))
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("1.1").title(Constants.documentApi).description("Documentation Document API v1.1").build());
	}
	@Bean
	public Docket swaggerDocumentApi12() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("aggregator-api-1.2")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage(Constants.aggreServ))
	            .paths(regex("/aggregator/v1.2.*"))
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("1.2").title(Constants.documentApi).description("Documentation Document API v1.2").build());
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Organization heros zuul gateway")
				.description("Sample REST API for centalized documentation using Spring Boot and spring-fox swagger 2 ")
				.contact(new Contact("Sukanta", "https://github.com/organization-heroes", "sukanta.workspace@gmail.com"))
				.version("1.0.0")
				.build();
	}


}