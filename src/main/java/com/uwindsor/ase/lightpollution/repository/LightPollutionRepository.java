package com.uwindsor.ase.lightpollution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uwindsor.ase.lightpollution.model.UserDetails;

public interface LightPollutionRepository extends JpaRepository<UserDetails, Long>{

}
