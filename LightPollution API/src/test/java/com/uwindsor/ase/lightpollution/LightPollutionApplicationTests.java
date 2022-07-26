package com.uwindsor.ase.lightpollution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uwindsor.ase.lightpollution.dto.MessageResponse;
import com.uwindsor.ase.lightpollution.dto.PeopleReportedCount;
import com.uwindsor.ase.lightpollution.dto.PollutionReportList;
import com.uwindsor.ase.lightpollution.dto.RecommendedPlaces;
import com.uwindsor.ase.lightpollution.dto.ReportLocation;
import com.uwindsor.ase.lightpollution.dto.SaveReportDto;
import com.uwindsor.ase.lightpollution.service.LightPollutionServiceImpl;

@SpringBootTest
class LightPollutionApplicationTests {
	@Autowired
	LightPollutionServiceImpl lightPollutionService = new LightPollutionServiceImpl();

	/*
	 * @Test void contextLoads() { }
	 */

	@Test
	@Order(1)
	public void testSaveLightPollutionReport() {
		ReportLocation reportLocation = new ReportLocation("current_location", "N6C 9C3", "Sample Street");
		SaveReportDto saveReportDto = new SaveReportDto("abc", "abc@sample.com", "BrightImage", reportLocation, "Outdoor", "Not shielded", "Bright", "White");
		ResponseEntity<MessageResponse> message = lightPollutionService.saveReport(saveReportDto);
		assertEquals(message.getStatusCode(), HttpStatus.OK);
		assertNotNull(message.getBody());
	}
	
	@Test
	@Order(2)
	public void testGetLightPollutionReport() {
		PollutionReportList pollutionReportList = lightPollutionService.fetchReport();
		assertNotNull(pollutionReportList);
		assertNotNull(pollutionReportList.getPollutionReports());
		assertNotEquals(pollutionReportList.getPollutionReports().size(), 0);
	}
	

	@Test
	public void testGetReportCount() {
		PeopleReportedCount peopleReportedCount = lightPollutionService.fetchNumberOfPeopleReported("Ontario");
		assertNotNull(peopleReportedCount);
		assertNotNull(peopleReportedCount.reported_incidents);
	}
	
	@Test
	public void testGetRecommendationsBasedOnProvince(){
		RecommendedPlaces places = lightPollutionService.fetchRecommendPlacesBasedOnProvince("ontario");
		assertNotNull(places);
		int numberOfPlaces = places.getRecomendedPlacesList().size();
		assertNotEquals(numberOfPlaces, 0);
	}
	
	@Test
	public void testGetRecommendationsBasedOnProvinceCapitalisation(){
		RecommendedPlaces places = lightPollutionService.fetchRecommendPlacesBasedOnProvince("Ontario");
		assertNotNull(places);
		int numberOfPlaces = places.getRecomendedPlacesList().size();
		assertNotEquals(numberOfPlaces, 0);
	}
	
	@Test
	public void testGetRecommendationsBasedOnProvinceWrongInput(){
		RecommendedPlaces places = lightPollutionService.fetchRecommendPlacesBasedOnProvince("New York");
		assertNotNull(places);
		assertNull(places.getRecomendedPlacesList());
	}
	
	@Test
	public void testGetRecommendationsBasedOnCity(){
		RecommendedPlaces places = lightPollutionService.fetchRecommendPlacesBasedOnCity("tobermory");
		assertNotNull(places);
		int numberOfPlaces = places.getRecomendedPlacesList().size();
		assertNotEquals(numberOfPlaces, 0);
	}
	
	@Test
	public void testGetRecommendationsBasedOnCityCapitalisation(){
		RecommendedPlaces places = lightPollutionService.fetchRecommendPlacesBasedOnCity("Tobermory");
		assertNotNull(places);
		int numberOfPlaces = places.getRecomendedPlacesList().size();
		assertNotEquals(numberOfPlaces, 0);
	}
	
	@Test
	public void testGetRecommendationsBasedOnCityWrongInput(){
		RecommendedPlaces places = lightPollutionService.fetchRecommendPlacesBasedOnCity("Chicago");
		assertNotNull(places);
		assertNull(places.getRecomendedPlacesList());
	}
	
}
