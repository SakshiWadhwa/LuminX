package com.uwindsor.ase.lightpollution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uwindsor.ase.lightpollution.model.LocationPollutionReportLink;
import com.uwindsor.ase.lightpollution.model.UserPollutionReportLink;

public interface UserPollutionReportLinkRepository extends JpaRepository<UserPollutionReportLink, Long>{

	@Query("select upr from UserPollutionReportLink upr where upr.locationPollutionReportLink = (:locationPollutionReportLink)")
	UserPollutionReportLink findIdByLocationPollutionLink(@Param("locationPollutionReportLink")  LocationPollutionReportLink locationPollutionReportLink);

}
