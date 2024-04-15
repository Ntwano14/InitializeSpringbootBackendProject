package com.numadic.restapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numadic.restapi.entity.Location;
import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.repository.LocationRepository;

@Service
public class LocationService {

	private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Simulate generating random location data for a vehicle
    public Location generateRandomLocationForVehicle(Vehicle vehicleId) {
        // Generate random latitude and longitude
        float latitude = (float) (ThreadLocalRandom.current().nextDouble(-90, 90));
        float longitude = (float) (ThreadLocalRandom.current().nextDouble(-180, 180));
        LocalDateTime timestamp = LocalDateTime.now();
        
        // Create and return a new Location object
        return new Location(latitude, longitude, timestamp, vehicleId);
    }
    
    public boolean isVehicleTracked(int vehicleId) {
        return locationRepository.existsByVehicleId(vehicleId);
    }

    
    public List<Location> getLocationsByVehicleId(int vehicleId) {
        return locationRepository.findByVehicleId(vehicleId);
    }


    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(int id) {
        return locationRepository.findById(id);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }
    
}