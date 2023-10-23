package com.example.Place.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long>{
	List<Place> findByName(String name);
	
	void deleteById(Long id);
	
	List<Place> getPlaceById(Long id);
}
