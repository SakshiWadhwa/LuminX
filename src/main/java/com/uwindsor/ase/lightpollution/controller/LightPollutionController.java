package com.uwindsor.ase.lightpollution.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uwindsor.ase.lightpollution.dto.MessageResponse;
import com.uwindsor.ase.lightpollution.dto.PollutionReportList;
import com.uwindsor.ase.lightpollution.dto.SaveReportDto;
import com.uwindsor.ase.lightpollution.service.LightPollutionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LightPollutionController {

	public static final Logger logger = LogManager.getLogger(LightPollutionController.class);
	
	@Autowired
	LightPollutionService lightPollutionervice;
	
	@PostMapping("lightPollution/report")
	public ResponseEntity<MessageResponse> saveReport(@RequestBody SaveReportDto saveReportDto){
		return lightPollutionervice.saveReport(saveReportDto);
	}
	
	@GetMapping("lightPollution/report")
	public PollutionReportList fetchReport(){
		return lightPollutionervice.fetchReport();
	}
}
