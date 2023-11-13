package com.example.Place;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import com.example.Place.domain.PlaceRepository;
import com.example.Place.security.*;
import com.example.Place.domain.Category;
import com.example.Place.domain.CategoryRepository;
import com.example.Place.domain.Place;

@SpringBootApplication
public class PlaceApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PlaceApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PlaceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(PlaceRepository placeRepository, CategoryRepository categoryRepository, UserRepository urepository) {
		return (args) -> {
			// Insert categories
			Category cafeCategory = new Category("Cafe");
			Category restaurantCategory = new Category("Restaurant");
			Category barCategory = new Category("Bar");
			categoryRepository.saveAll(Arrays.asList(cafeCategory, restaurantCategory, barCategory));
			
			// Create places and associate them with categories (Name, Location, Price Level)
			Place p1 = new Place("Mala House", "Pasila", "Middle");
			p1.setCategory(restaurantCategory);
			Place p2 = new Place("Cafe Amore", "Oulunkylä", "Low");
			p2.setCategory(cafeCategory);
			Place p3 = new Place("Grape Wine Bar", "Erottaja", "High");
			p3.setCategory(barCategory);
			placeRepository.saveAll(Arrays.asList(p1, p2, p3));
			
			// USER
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			urepository.saveAll(Arrays.asList(user1, user2));
		};
	}
}