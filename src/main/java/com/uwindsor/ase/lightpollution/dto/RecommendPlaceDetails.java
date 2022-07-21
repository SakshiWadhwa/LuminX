package com.uwindsor.ase.lightpollution.dto;

public class RecommendPlaceDetails {

	private String category;
	private String name;
	private String type;
	private String city;
	private String continent;
	
	public RecommendPlaceDetails() {}
	
	public RecommendPlaceDetails(String category, String name, String type, String city, String continent) {
		super();
		this.category = category;
		this.name = name;
		this.type = type;
		this.city = city;
		this.continent = continent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}
}
