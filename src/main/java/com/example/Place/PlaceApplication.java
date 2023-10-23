package com.example.Place;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Place.domain.PlaceRepository;
import com.example.Place.domain.Category;
import com.example.Place.domain.CategoryRepository;
import com.example.Place.domain.Place;

@SpringBootApplication
public class PlaceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PlaceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlaceRepository placeRepository, CategoryRepository categoryRepository) {
	    return (args) -> {
	        // Insert categories
	        Category cafeCategory = new Category("Cafe");
	        Category restaurantCategory = new Category("Restaurant");
	        Category barCategory = new Category("Bar");

	        categoryRepository.save(cafeCategory);
	        categoryRepository.save(restaurantCategory);
	        categoryRepository.save(barCategory);

	        // Create places and associate them with categories (Name, Location, Price Level)
	        Place p1 = new Place("Mala House", "Pasila", "Middle");
	        p1.setCategory(restaurantCategory);

	        Place p2 = new Place("Cafe Amore", "Oulunkylä", "Low");
	        p2.setCategory(cafeCategory);

	        Place p3 = new Place("Grape Wine Bar", "Erottaja", "High");
	        p3.setCategory(barCategory);

	        placeRepository.save(p1);
	        placeRepository.save(p2);
	        placeRepository.save(p3);
	    };
	}
}
