package com.ibrahim.sample.rest.service;

import com.ibrahim.sample.rest.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleRestServiceApplication implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(SampleRestServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		productService.addNewProduct("Samsung A21", "Smartphone", 21000.00);
		productService.addNewProduct("Haylou LS02", "Smartwatch", 2500.00);
		productService.addNewProduct("Airplan Toy", "Toy", 250.00);
	}
}
