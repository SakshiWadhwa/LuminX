package com.uwindsor.ase.lightpollution.dto;

import java.util.List;

public class RecommendedPlaces {

	public String message;
	private List <RecommendPlaceDetails> RecomendedPlacesList;

	public RecommendedPlaces() {}

	public RecommendedPlaces(String message, List<RecommendPlaceDetails> recomendedPlacesList) {
		super();
		this.message = message;
		RecomendedPlacesList = recomendedPlacesList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<RecommendPlaceDetails> getRecomendedPlacesList() {
		return RecomendedPlacesList;
	}

	public void setRecomendedPlacesList(List<RecommendPlaceDetails> recomendedPlacesList) {
		RecomendedPlacesList = recomendedPlacesList;
	}
}
