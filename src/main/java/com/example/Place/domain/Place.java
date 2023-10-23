package com.example.Place.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "places")
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String location;
	private String priceLevel;
	
	public Place () {}
	
	public Place (String name, String location, String priceLevel){
		super();
		this.name = name;
		this.location = location;
		this.priceLevel = priceLevel;
	}
	
	//GET
	public Long getId() {
        return id;
    }
	public String getName(){
		return name;
	}
	public String getLocation(){
		return location;
	}
	public String getPriceLevel(){
		return priceLevel;
	}
	
	//SET
	public void setName(String name){
		this.name = name;
	}
	public void setLocation(String location){
		this.location = location;
	}
	public void setPriceLevel(String priceLevel){
		this.priceLevel = priceLevel;
	}
	
	//Category
	@ManyToOne
    @JoinColumn(name = "category_id")
	@JsonIgnore
    private Category category;
	
	public Category getCategory() {
	    return category;
	}

	public void setCategory(Category category) {
	    this.category = category;
	}

}
