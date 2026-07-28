package com.example.bank_account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankAccountApplication {

	private static final Logger log = LoggerFactory.getLogger(BankAccountApplication.class);

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
		};
	}

}
