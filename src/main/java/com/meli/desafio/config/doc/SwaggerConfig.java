package com.meli.desafio.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI(@Value("${application.version}") String appVersion) {
		return new OpenAPI()
				.info(new Info().title("URL shortener")
						.description("API encargada de cortar URL")
						.version(appVersion)
						.termsOfService("http://codmind.com/terms")
						.contact(new Contact().name("Admin").url("#").email("jpulgar1978@gmail.com"))
						.extensions(Collections.emptyMap())
				);
	}
}
