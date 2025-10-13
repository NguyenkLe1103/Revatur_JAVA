package com.test.shop;

import java.math.BigDecimal;




import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.shop.Product.Product;
import com.test.shop.Product.repo.ProductRepository;
import com.test.shop.Product.Category;
import com.test.shop.Product.repo.CategoryRepo;
@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	@Bean
	CommandLineRunner seed(CategoryRepo catRepo, ProductRepository prodRepo) {
	return args -> {
	if (catRepo.count() == 0) {
		Category electronics = catRepo.save(new Category(null, "Electronics", "Gadgets & devices"));
		Category books = catRepo.save(new Category(null, "Books", "Printed & digital"));


	if (prodRepo.count() == 0) {
		prodRepo.save(Product.builder()
		.name("USB-C Charger")
		.description("65W fast charger")
		.price(new BigDecimal("24.99"))
		.quantity(100)
		.category(electronics)
		.build());


		prodRepo.save(Product.builder()
		.name("Programming 101")
		.description("Beginner-friendly book")
		.price(new BigDecimal("39.90"))
		.quantity(50)
		.category(books)
		.build());
	}
		}
};
}
}

