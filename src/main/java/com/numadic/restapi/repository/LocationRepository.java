package com.numadic.restapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numadic.restapi.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

	List<Location> findByVehicleId(int vehicleId);
	boolean existsByVehicleId(int vehicleId);


}
