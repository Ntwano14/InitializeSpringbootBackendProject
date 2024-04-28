// LocationController.java
package com.numadic.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.numadic.restapi.entity.Location;
import com.numadic.restapi.exception.ResourceNotFoundException;
import com.numadic.restapi.service.LocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LocationController {

    private final LocationService locationService;
   // private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Integer id) {
        Location location = locationService.getLocationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        return ResponseEntity.ok(location);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Location> createLocation(@Valid @RequestBody Location location) {
        Location savedLocation = locationService.saveLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Integer id, @Valid @RequestBody Location updatedLocation) {
        // Check if the location exists
        Optional<Location> optionalLocation = locationService.getLocationById(id);
        if (optionalLocation.isEmpty()) {
            throw new ResourceNotFoundException("Location not found with id: " + id);
        }
        
        // Set the id of the updated location to match the path variable
        updatedLocation.setId(id);
        
        // Update the location
        Location updatedLocationResult = locationService.saveLocation(updatedLocation);
        
        // Return the updated location with a success status
        return ResponseEntity.ok(updatedLocationResult);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable Integer id) {
        Optional<Location> optionalLocation = locationService.getLocationById(id);
        if (optionalLocation.isEmpty()) {
            throw new ResourceNotFoundException("Location not found with id: " + id);
        }
        locationService.deleteLocation(id);
    }

    @GetMapping("/latest/{vehicleId}")
    public ResponseEntity<Location> getLatestLocation(@PathVariable Integer vehicleId) {
        Location latestLocation = locationService.getLatestLocationByVehicleId(vehicleId);
        if (latestLocation == null) {
            // Return 404 Not Found if location data is not available for the specified vehicle ID
            throw new ResourceNotFoundException("Latest location not found for vehicle with id: " + vehicleId);
        }
        return ResponseEntity.ok(latestLocation);
    }
}
