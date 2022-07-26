package com.uwindsor.ase.lightpollution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uwindsor.ase.lightpollution.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

	@Query("select ud from UserDetails ud where ud.mail = (:mail)")
	UserDetails fetchUserRecord(@Param("mail") String mail);


}

