package com.numadic.restapi.repository;

import com.numadic.restapi.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    boolean existsByRegistrationNumber(String registrationNumber);
}
