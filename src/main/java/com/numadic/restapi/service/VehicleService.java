package com.numadic.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.exception.ResourceNotFoundException;
import com.numadic.restapi.repository.VehicleRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle registerVehicle(Vehicle vehicle) {
        String registrationNumber = vehicle.getRegistrationNumber();

        if (vehicleRepository.existsByRegistrationNumber(registrationNumber)) {
            throw new RuntimeException("Vehicle with registration number " + registrationNumber + " already exists");
        }

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle updateVehicle(Integer id, Vehicle updatedVehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));

        updatedVehicle.setId(existingVehicle.getId());
        return vehicleRepository.save(updatedVehicle);
    }

    public void removeVehicle(Integer id) {
        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }
}

