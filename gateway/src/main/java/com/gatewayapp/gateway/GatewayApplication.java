package com.gatewayapp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({ "com.gatewayapp.gateway", "com.gatewayapp.config", "com.gatewayapp.controllers",
		"com.gatewayapp.services" })
@EntityScan("com.gatewayapp.entities")
@EnableJpaRepositories(basePackages = { "com.gatewayapp.repositories" })
@EnableScheduling
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
