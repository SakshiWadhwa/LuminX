package com.uwindsor.ase.lightpollution.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
    public Docket asbApi() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.uwindsor.ase.lightpollution.controller"))              
          .paths(PathSelectors.any()).build()
          .pathMapping("/")
          .directModelSubstitute(LocalDate.class, String.class)
          .genericModelSubstitutes(ResponseEntity.class)
          .useDefaultResponseMessages(false)
          .apiInfo(apiInfo())
          .enableUrlTemplating(true);
    }

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.description("ASE Project")
				.title("LightPollution")
				.build();
	}
}
