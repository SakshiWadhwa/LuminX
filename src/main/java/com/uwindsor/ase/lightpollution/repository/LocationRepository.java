package com.uwindsor.ase.lightpollution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uwindsor.ase.lightpollution.model.LocationMaster;

public interface LocationRepository extends JpaRepository<LocationMaster, Long>{

	@Query("select lm from LocationMaster lm where lm.zip_code = (:zip_code) AND lm.street_name = (:street_name) AND lm.current_location = (:current_location)")
	LocationMaster searchLocationByAll(@Param("zip_code") String zip_code, @Param("street_name") String street_name, @Param("current_location") String current_location);

	@Query("select lm from LocationMaster lm where lm.zip_code = (:zip_code)")
	LocationMaster searchLocationByZipCode(@Param("zip_code") String zip_code);

	@Query("select lm from LocationMaster lm where lm.street_name = (:street_name)")
	LocationMaster searchLocationByStreetName(@Param("street_name") String street_name);

	@Query("select lm from LocationMaster lm where lm.current_location = (:current_location)")
	LocationMaster searchLocationByCurrentLocation(@Param("current_location") String current_location);

	@Query("select lm from LocationMaster lm where lm.zip_code = (:location) OR lm.street_name = (:location) OR lm.current_location = (:location)")
	LocationMaster findIdByLocation(@Param("location") String location);

}
