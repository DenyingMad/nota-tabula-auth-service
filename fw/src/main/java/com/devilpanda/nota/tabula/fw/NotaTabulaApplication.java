package com.devilpanda.nota.tabula.fw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.devilpanda.nota.tabula")
@EnableJpaRepositories(basePackages = "com.devilpanda.nota.tabula.adapter.jpa")
@EntityScan(basePackages = "com.devilpanda.nota.tabula")
public class NotaTabulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotaTabulaApplication.class, args);
	}

}
