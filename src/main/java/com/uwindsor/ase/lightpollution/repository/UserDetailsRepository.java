package com.uwindsor.ase.lightpollution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uwindsor.ase.lightpollution.dto.SaveReportDto;
import com.uwindsor.ase.lightpollution.model.PollutionReport;
import com.uwindsor.ase.lightpollution.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

  @Query("select ud from UserDetails ud where ud.mail = (:mail)")
	UserDetails fetchUserRecord(@Param("mail") String mail);

}
