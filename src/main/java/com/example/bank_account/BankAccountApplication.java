package com.example.bank_account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BankAccountApplication {

	private static final Logger log = LoggerFactory.getLogger(BankAccountApplication.class);

	private final Environment environment;

	public BankAccountApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	@Bean
	CommandLineRunner mongoUriDiagnostic() {
		return args -> {
			String uri = System.getenv("SPRING_DATA_MONGODB_URI");
			boolean present = uri != null && !uri.isBlank();
			log.info("[DIAG] SPRING_DATA_MONGODB_URI present={}, length={}",
					present, present ? uri.length() : 0);
			String springUri = environment.getProperty("spring.data.mongodb.uri");
			boolean springPresent = springUri != null && !springUri.isBlank();
			log.info("[DIAG] spring.data.mongodb.uri present={}, length={}",
					springPresent, springPresent ? springUri.length() : 0);
		};
	}

	@Bean
	CommandLineRunner checkMongoProperties(MongoProperties mongoProperties) {
		return args -> {
			String uri = mongoProperties.getUri();
			log.info("[DIAG] MongoProperties.getUri() present={}, length={}",
					uri != null, uri != null ? uri.length() : 0);
			log.info("[DIAG] MongoProperties.getHost()={}", mongoProperties.getHost());
		};
	}

}
