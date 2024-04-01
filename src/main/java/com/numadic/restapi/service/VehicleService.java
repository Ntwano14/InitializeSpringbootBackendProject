package com.numadic.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.repository.VehicleRepository;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;

	@Autowired
	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	// Method to retrieve all vehicles from the database
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	// Method to retrieve a vehicle by its ID
	public Vehicle getVehicleById(Long id) {
		return vehicleRepository.findById(id).orElse(null);
	}

	// Method to save a new vehicle
	public Vehicle saveVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	// Method to update an existing vehicle
	public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
		Vehicle existingVehicle = vehicleRepository.findById(id).orElse(null);
		if (existingVehicle != null) {
			existingVehicle.setModel(updatedVehicle.getModel());
			existingVehicle.setMake(updatedVehicle.getMake());
			existingVehicle.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
			existingVehicle.setOwnerName(updatedVehicle.getOwnerName());
			existingVehicle.setOwnerContactNumber(updatedVehicle.getOwnerContactNumber());
			return vehicleRepository.save(existingVehicle);
		} else {
			return null; // Handle the case where the vehicle with the given ID doesn't exist
		}
	}

	// Method to delete a vehicle by its ID
	public void deleteVehicle(Long id) {
		vehicleRepository.deleteById(id);
	}

	// Method to find vehicles by owner name
	public List<Vehicle> findVehiclesByOwnerName(String ownerName) {
		return vehicleRepository.findByOwnerName(ownerName);
	}
}
