package com.numadic.restapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numadic.restapi.entity.Location;
import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.exception.ResourceNotFoundException;
import com.numadic.restapi.repository.LocationRepository;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location generateRandomLocationForVehicle(Vehicle vehicle) {
        double latitude = ThreadLocalRandom.current().nextDouble(-90, 90);
        double longitude = ThreadLocalRandom.current().nextDouble(-180, 180);
        LocalDateTime timestamp = LocalDateTime.now();

        return new Location(latitude, longitude, timestamp, vehicle);
    }

    public boolean isVehicleTracked(Integer vehicleId) {
        return locationRepository.existsByVehicleId(vehicleId);
    }

    public List<Location> getLocationsByVehicleId(Integer vehicleId) {
        return locationRepository.findByVehicleId(vehicleId);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Integer id) {
        return locationRepository.findById(id);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(Integer id) {
        if (!locationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
    }
    
    public Location getLatestLocationByVehicleId(Integer vehicleId) {
        return locationRepository.findTopByVehicleIdOrderByTimestampDesc(vehicleId);
    }
 
}
