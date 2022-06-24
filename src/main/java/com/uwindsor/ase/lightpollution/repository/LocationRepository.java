package com.uwindsor.ase.lightpollution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uwindsor.ase.lightpollution.model.LocationMaster;

public interface LocationRepository extends JpaRepository<LocationMaster, Long>{

}
