package com.numadic.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.numadic.restapi.entity.Location;
import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.service.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {

	private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/latest/{vehicleId}")
    public ResponseEntity<Location> getLatestLocation(@PathVariable Vehicle vehicleId) {
        Location latestLocation = locationService.generateRandomLocationForVehicle(vehicleId);
        return ResponseEntity.ok(latestLocation);
    }

    @GetMapping("/view")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable int id) {
        return locationService.getLocationById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Location createLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
    }
}
