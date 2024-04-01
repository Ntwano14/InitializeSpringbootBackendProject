package com.numadic.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numadic.restapi.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	List<Vehicle> findByOwnerName(String ownerName);

}
