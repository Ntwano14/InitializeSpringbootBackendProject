package com.numadic.restapi.service;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.repository.VehicleRepository;

@Service
public class VehicleService {

	private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

	private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Method to check if a vehicle exists by its ID
    public boolean existsById(int id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        return vehicleOptional.isPresent();
    }
	
    // Method to register a new vehicle if it does not exist
    public Vehicle registerVehicle(Vehicle vehicle) {
        String registrationNumber = vehicle.getRegistrationNumber();
        
        // Check if a vehicle with the provided registration number already exists
        if (vehicleRepository.existsByRegistrationNumber(registrationNumber)) {
            String errorMessage = "Vehicle with registration number " + registrationNumber + " already exists";
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        
        // Save the vehicle if it's not already registered
        return vehicleRepository.save(vehicle);
    }


	// Method to retrieve all vehicles from the database
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

	// Method to retrieve a vehicle by its ID
	public Optional<Vehicle> getVehicleById(int id) {
        return vehicleRepository.findById(id);
    }

//	// Method to register a new vehicle
//	 public Vehicle registerVehicle(Vehicle vehicle) {
//	        return vehicleRepository.save(vehicle);
//	    }

	// Method to update an existing vehicle
	public Vehicle updateVehicle(int id, Vehicle updatedVehicle) {
        if (vehicleRepository.existsById(id)) {
            updatedVehicle.setId(id);
            return vehicleRepository.save(updatedVehicle);
        } else {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
    }

	// Method to remove a vehicle by its ID
	public void removeVehicle(int id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
    }

}
