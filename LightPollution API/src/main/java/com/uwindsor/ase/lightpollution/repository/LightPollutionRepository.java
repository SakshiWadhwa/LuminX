package com.uwindsor.ase.lightpollution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uwindsor.ase.lightpollution.model.PollutionReport;
import com.uwindsor.ase.lightpollution.model.RecommendPlace;

public interface LightPollutionRepository extends JpaRepository<PollutionReport, Long>{

	@Query("select pr from PollutionReport pr where pr.image = (:image) AND pr.light_source = (:light_source) AND pr.brightness = (:brightness)")
	PollutionReport findPollutionReport(@Param("image") String image, @Param("light_source") String light_source, @Param("brightness") String brightness);

	@Query("select rp from RecommendPlace rp where LOWER(rp.province) = LOWER((:province))")
	List<RecommendPlace> findPlacesByProvince(@Param("province") String province);

	@Query("select rp from RecommendPlace rp where LOWER(rp.city) = LOWER((:city))")
	List<RecommendPlace> findPlacesByCity(@Param("city") String city);

}
