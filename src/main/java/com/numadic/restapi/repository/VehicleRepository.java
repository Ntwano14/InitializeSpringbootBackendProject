package com.numadic.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numadic.restapi.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}
