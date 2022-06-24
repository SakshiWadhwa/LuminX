package com.uwindsor.ase.lightpollution.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uwindsor.ase.lightpollution.dto.MessageResponse;
import com.uwindsor.ase.lightpollution.dto.PollutionReportList;
import com.uwindsor.ase.lightpollution.dto.SaveReportDto;

@Service
public interface LightPollutionService {

	ResponseEntity<MessageResponse> saveReport(SaveReportDto saveReportDto);

	PollutionReportList fetchReport();

}
