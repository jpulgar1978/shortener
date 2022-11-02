package com.meli.desafio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.meli.desafio.DesafioApplication;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@ComponentScan(basePackageClasses=DesafioApplication.class)
public class DatabaseConfig {
	
	@Value("${spring.data.mongodb.uri}")
	private String uriDb;
	
	@Value("${spring.data.mongodb.database}")
	private String dbName;
	
	public @Bean MongoClient mongoClient() {
		return MongoClients.create(uriDb);
	}

	public @Bean
	MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), dbName);
	}

}
