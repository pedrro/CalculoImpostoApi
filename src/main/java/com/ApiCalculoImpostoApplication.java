package com;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ApiCalculoImpostoApplication extends AbstractMongoConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(ApiCalculoImpostoApplication.class, args);
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}

	@Override
	protected String getDatabaseName() {
		return "UsuarioDb";
	}
}
