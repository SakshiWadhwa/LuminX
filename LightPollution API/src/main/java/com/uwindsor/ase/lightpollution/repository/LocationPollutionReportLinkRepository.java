package com.uwindsor.ase.lightpollution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uwindsor.ase.lightpollution.model.LocationMaster;
import com.uwindsor.ase.lightpollution.model.LocationPollutionReportLink;
import com.uwindsor.ase.lightpollution.model.PollutionReport;

public interface LocationPollutionReportLinkRepository extends JpaRepository<LocationPollutionReportLink, Long>{

	@Query("select lpr from LocationPollutionReportLink lpr where lpr.pollutionReport = (:pollutionReportCounter)")
	LocationPollutionReportLink findByPollutionReportId(@Param("pollutionReportCounter") PollutionReport pollutionReportCounter);

	@Query("select lpr from LocationPollutionReportLink lpr where lpr.locationMaster = (:lm)")
	List<LocationPollutionReportLink> fetchLocationPollutionLinkId(@Param("lm") LocationMaster lm);
}

