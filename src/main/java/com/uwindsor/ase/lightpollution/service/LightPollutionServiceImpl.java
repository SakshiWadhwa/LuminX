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
import com.uwindsor.ase.lightpollution.dto.PollutionReportList;
import com.uwindsor.ase.lightpollution.dto.PollutionReports;
import com.uwindsor.ase.lightpollution.dto.ReportLocation;
import com.uwindsor.ase.lightpollution.dto.SaveReportDto;
import com.uwindsor.ase.lightpollution.model.LocationMaster;
import com.uwindsor.ase.lightpollution.model.LocationPollutionReportLink;
import com.uwindsor.ase.lightpollution.model.PollutionReport;
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
	
	@Override
	public ResponseEntity<MessageResponse> saveReport(SaveReportDto saveReportDto) {
		MessageResponse messageResponse = new MessageResponse();
		
		if(null == saveReportDto.getLocation().getCurrent_location() ||
				null == saveReportDto.getLocation().getStreet_name() ||
				null == saveReportDto.getLocation().getZip_code()) {
			
			messageResponse.setMessage("Please enter location");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
		}
		else {
			UserDetails userDetails = new UserDetails(saveReportDto.getName(), saveReportDto.getEmail());
			UserDetails saveUserDetails = userDetailsRepository.save(userDetails);
			
			PollutionReport pollutionReport = new PollutionReport(saveReportDto.getImage(), saveReportDto.getLight_source(),
					saveReportDto.getStyle(), saveReportDto.getBrightness(), saveReportDto.getLight_color(), date);
			PollutionReport savePollutionReport = lightPollutionRepository.save(pollutionReport);
			
			LocationMaster locationMaster = new LocationMaster(saveReportDto.getLocation().getCurrent_location(),
					saveReportDto.getLocation().getZip_code(), saveReportDto.getLocation().getStreet_name());
			LocationMaster saveLocationMaster = locationRepository.save(locationMaster);
			
			LocationPollutionReportLink locationPollutionReportLink = new LocationPollutionReportLink(saveLocationMaster, savePollutionReport, date);
			LocationPollutionReportLink saveLocationPollutionReportLink = locationPollutionReportLinkRepository.save(locationPollutionReportLink);
			
			UserPollutionReportLink userPollutionReportLink = new UserPollutionReportLink(saveUserDetails,saveLocationPollutionReportLink,date);
			userPollutionReportLinkRepository.save(userPollutionReportLink);
			
			messageResponse.setMessage("Report saved successfully");
			return new ResponseEntity<>(messageResponse, HttpStatus.OK);
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

}
