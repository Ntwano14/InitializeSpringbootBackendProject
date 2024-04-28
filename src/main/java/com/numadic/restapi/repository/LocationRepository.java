package com.numadic.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numadic.restapi.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByVehicleId(Integer vehicleId);

    boolean existsByVehicleId(Integer vehicleId);

	Location findTopByVehicleIdOrderByTimestampDesc(Integer vehicleId);
}
