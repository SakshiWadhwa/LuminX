package com.uwindsor.ase.lightpollution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uwindsor.ase.lightpollution.model.PollutionReport;

public interface LightPollutionRepository extends JpaRepository<PollutionReport, Long>{

}
