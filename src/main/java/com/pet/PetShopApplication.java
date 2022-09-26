package com.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
		System.out.println("Proyecto Cargado");
	}

}
