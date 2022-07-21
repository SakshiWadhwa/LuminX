package com.uwindsor.ase.lightpollution.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uwindsor.ase.lightpollution.dto.MessageResponse;
import com.uwindsor.ase.lightpollution.dto.PeopleReportedCount;
import com.uwindsor.ase.lightpollution.dto.PollutionReportList;
import com.uwindsor.ase.lightpollution.dto.PollutionReports;
import com.uwindsor.ase.lightpollution.dto.RecommendPlaceDetails;
import com.uwindsor.ase.lightpollution.dto.RecommendedPlaces;
import com.uwindsor.ase.lightpollution.dto.ReportLocation;
import com.uwindsor.ase.lightpollution.dto.SaveReportDto;
import com.uwindsor.ase.lightpollution.model.LocationMaster;
import com.uwindsor.ase.lightpollution.model.LocationPollutionReportLink;
import com.uwindsor.ase.lightpollution.model.PollutionReport;
import com.uwindsor.ase.lightpollution.model.RecommendPlace;
import com.uwindsor.ase.lightpollution.model.UserDetails;
import com.uwindsor.ase.lightpollution.model.UserPollutionReportLink;
import com.uwindsor.ase.lightpollution.repository.LightPollutionRepository;
import com.uwindsor.ase.lightpollution.repository.LocationPollutionReportLinkRepository;
import com.uwindsor.ase.lightpollution.repository.LocationRepository;
import com.uwindsor.ase.lightpollution.repository.UserDetailsRepository;
import com.uwindsor.ase.lightpollution.repository.UserPollutionReportLinkRepository;

@Service
public class LightPollutionServiceImpl implements LightPollutionService{

	@Autowired
	LightPollutionRepository lightPollutionRepository;
	@Autowired
	UserDetailsRepository userDetailsRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	LocationPollutionReportLinkRepository locationPollutionReportLinkRepository;
	@Autowired
	UserPollutionReportLinkRepository userPollutionReportLinkRepository;
	
	LocalDateTime localDateTime = LocalDateTime.now();
	Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
	Date date = Date.from(instant);
	
	int count = 0;
	
	@Override
	public ResponseEntity<MessageResponse> saveReport(SaveReportDto saveReportDto) {
		MessageResponse messageResponse = new MessageResponse();
		count = 0;
		
		if(null == saveReportDto.getLocation().getCurrent_location() &&
				null == saveReportDto.getLocation().getStreet_name() &&
				null == saveReportDto.getLocation().getZip_code()) {
			
			messageResponse.setMessage("Please enter location");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		}
		else {
			LocationMaster saveLocationMaster = findAndSaveLocation(saveReportDto);
			UserDetails saveUserDetails = saveUserDetails(saveReportDto);
			PollutionReport savePollutionReport = findAndSavePollutionReport(saveReportDto);
			
			if(count == 3) {
				messageResponse.setMessage("Report is already submitted");
				return new ResponseEntity<>(messageResponse, HttpStatus.OK);
			}
			else {
				LocationPollutionReportLink locationPollutionReportLink = new LocationPollutionReportLink(saveLocationMaster, savePollutionReport, date);
				LocationPollutionReportLink saveLocationPollutionReportLink = locationPollutionReportLinkRepository.save(locationPollutionReportLink);
				
				UserPollutionReportLink userPollutionReportLink = new UserPollutionReportLink(saveUserDetails,saveLocationPollutionReportLink,date);
				userPollutionReportLinkRepository.save(userPollutionReportLink);
				
				messageResponse.setMessage("Report saved successfully");
				return new ResponseEntity<>(messageResponse, HttpStatus.OK);
			}	
		}
	}

	private PollutionReport findAndSavePollutionReport(SaveReportDto saveReportDto) {
		PollutionReport pr = lightPollutionRepository.findPollutionReport(saveReportDto.getImage(), saveReportDto.getLight_source(), saveReportDto.getBrightness());
		if(null == pr) {
			PollutionReport pollutionReport = new PollutionReport(saveReportDto.getImage(), saveReportDto.getLight_source(),saveReportDto.getStyle(), saveReportDto.getBrightness(), saveReportDto.getLight_color(), date);
			PollutionReport savePollutionReport = lightPollutionRepository.save(pollutionReport);
			return savePollutionReport;
		}
		else {
			count++;
			return pr;
		}
	}
	
	private UserDetails saveUserDetails(SaveReportDto saveReportDto) {
		UserDetails ud = userDetailsRepository.fetchUserRecord(saveReportDto.getEmail());
		if(null == ud) {
			UserDetails userDetails = new UserDetails(saveReportDto.getName(), saveReportDto.getEmail());
			UserDetails saveUserDetails = userDetailsRepository.save(userDetails);

			return saveUserDetails;
		}
		else {
			count++;
			return ud;
		}
	}

	private LocationMaster findAndSaveLocation(SaveReportDto saveReportDto) {
		LocationMaster saveLocationMaster = null;
		if(null != saveReportDto.getLocation().getZip_code() && null != saveReportDto.getLocation().getStreet_name() && null != saveReportDto.getLocation().getCurrent_location())
		{
			LocationMaster lm = locationRepository.searchLocationByAll(saveReportDto.getLocation().getZip_code(),
					saveReportDto.getLocation().getStreet_name(), saveReportDto.getLocation().getCurrent_location());
			
			saveLocationMaster = saveLocation(lm, saveReportDto);	
		}
		else if(null != saveReportDto.getLocation().getZip_code())
		{
			LocationMaster lm = locationRepository.searchLocationByZipCode(saveReportDto.getLocation().getZip_code());
			saveLocationMaster = saveLocation(lm, saveReportDto);
		}
		else if(null != saveReportDto.getLocation().getStreet_name())
		{
			LocationMaster lm = locationRepository.searchLocationByStreetName(saveReportDto.getLocation().getStreet_name());
			saveLocationMaster = saveLocation(lm, saveReportDto);
		}
		else if(null != saveReportDto.getLocation().getCurrent_location()) {
			LocationMaster lm = locationRepository.searchLocationByCurrentLocation(saveReportDto.getLocation().getCurrent_location());
			saveLocationMaster = saveLocation(lm, saveReportDto);
		}
		return saveLocationMaster;	
	}

	private LocationMaster saveLocation(LocationMaster lm, SaveReportDto saveReportDto) {
		if(null == lm) {
			LocationMaster locationMaster = new LocationMaster(saveReportDto.getLocation().getCurrent_location(),
					saveReportDto.getLocation().getZip_code(), saveReportDto.getLocation().getStreet_name());
			LocationMaster saveLocationMaster = locationRepository.save(locationMaster);
			return saveLocationMaster;
		}
		else {
			count++;
			return lm;
		}
	}

	@Override
	public PollutionReportList fetchReport() {
		PollutionReportList pollutionReportListData = null;
		List<PollutionReports> pollutionReportsList = new ArrayList<>();

		List<PollutionReport> fetchPollutionReport = lightPollutionRepository.findAll();
		for(PollutionReport pollutionReportCounter : fetchPollutionReport) {
			PollutionReports pollutionReports;
			
			if(null != fetchPollutionReport) {
				LocationPollutionReportLink locationPollutionReportLink = locationPollutionReportLinkRepository.findByPollutionReportId(pollutionReportCounter);
				UserPollutionReportLink userPollutionReportLink = userPollutionReportLinkRepository.findIdByLocationPollutionLink(locationPollutionReportLink);
				
				ReportLocation reportLocation = new ReportLocation(locationPollutionReportLink.getLocationMaster().getCurrent_location(),
						locationPollutionReportLink.getLocationMaster().getZip_code(),
						locationPollutionReportLink.getLocationMaster().getStreet_name());
				
				pollutionReports = new PollutionReports(userPollutionReportLink.getUserDetails().getName(),
						userPollutionReportLink.getUserDetails().getMail(),
						pollutionReportCounter.getImage(), reportLocation,
						pollutionReportCounter.getLight_source(),
						pollutionReportCounter.getStyle(),
						pollutionReportCounter.getBrightness(),
						pollutionReportCounter.getLight_color(),
						pollutionReportCounter.getDate());
						
				pollutionReportsList.add(pollutionReports);
			}
		}
		pollutionReportListData = new PollutionReportList(pollutionReportsList);
		return pollutionReportListData;
	}

	@Override
	public PeopleReportedCount fetchNumberOfPeopleReported(String location) {
		PeopleReportedCount peopleReportedCount = new PeopleReportedCount();
		if(null == location) {
			peopleReportedCount.setMessage("Please provide location to fetch the count of reported pollutions.");
		}
		else {
			LocationMaster lm = locationRepository.findIdByLocation(location);
			if(null == lm)
				peopleReportedCount.setReported_incidents(0);
			else {
				List<LocationPollutionReportLink> lprList =  locationPollutionReportLinkRepository.fetchLocationPollutionLinkId(lm);
				
				System.out.println("count is: " + lprList.size());
				peopleReportedCount.setReported_incidents(lprList.size());
			}		
		}
		return peopleReportedCount;
	}

	@Override
	public RecommendedPlaces fetchRecommendPlacesBasedOnProvince(String province) {
		RecommendedPlaces rp = new RecommendedPlaces();
		List<RecommendPlaceDetails> rpdList = new ArrayList<>();
		RecommendPlaceDetails rpd = null;
		
		if(null != province) {
			List<RecommendPlace> recommendPlace = lightPollutionRepository.findPlacesByProvince(province);
			if(recommendPlace.isEmpty())
				rp.setMessage("No recommendations for province " + province + ".");
			else {
				for(RecommendPlace rpCounter : recommendPlace) {
					rpd= new RecommendPlaceDetails(rpCounter.getCategory(), rpCounter.getName(), rpCounter.getType(), rpCounter.getCity(), rpCounter.getContinent());
					rpdList.add(rpd);
				}
				rp.setMessage("Recommending " + recommendPlace.size() + " places to visit...!!!");
				rp.setRecomendedPlacesList(rpdList);
			}	
		}
		else {
			rp.setMessage("Please enter province to fetch recommended places..!!!");
		}
		return rp;
	}

	@Override
	public RecommendedPlaces fetchRecommendPlacesBasedOnCity(String city) {
		RecommendedPlaces rp = new RecommendedPlaces();
		List<RecommendPlaceDetails> rpdList = new ArrayList<>();
		RecommendPlaceDetails rpd = null;
		
		if(null != city) {
			List<RecommendPlace> recommendPlace = lightPollutionRepository.findPlacesByCity(city);
			if(recommendPlace.isEmpty())
				rp.setMessage("No recommendations for City " + city + ".");
			else {
				for(RecommendPlace rpCounter : recommendPlace) {
					rpd= new RecommendPlaceDetails(rpCounter.getCategory(), rpCounter.getName(), rpCounter.getType(), rpCounter.getCity(), rpCounter.getContinent());
					rpdList.add(rpd);
				}
				rp.setMessage("Recommending " + recommendPlace.size() + " places to visit...!!!");
				rp.setRecomendedPlacesList(rpdList);
			}	
		}
		else {
			rp.setMessage("Please enter province to fetch recommended places..!!!");
		}
		return rp;
	}

}
