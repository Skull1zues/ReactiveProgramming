package com.soumya._stWebFluxApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.soumya._stWebFluxApplication.${sec}")
@EnableR2dbcRepositories(basePackages = "com.soumya._stWebFluxApplication.${sec}")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
