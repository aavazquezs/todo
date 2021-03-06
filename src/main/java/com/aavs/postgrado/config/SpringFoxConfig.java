package com.aavs.postgrado.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	private ApiInfo getApiInfo() {
        return new ApiInfo(
                "API REST ToDo",
                "API REST para el curso Spring Boot",
                "1.0",
                "",
                new Contact("Angel Alberto Vazquez Sánchez", "", "aavazquez@uci.cu"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
	}
}
