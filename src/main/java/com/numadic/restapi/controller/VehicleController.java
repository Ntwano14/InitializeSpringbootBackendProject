package com.numadic.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.service.LocationService;
import com.numadic.restapi.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	 private final VehicleService vehicleService;
	    private final LocationService locationService;

	    @Autowired
	    public VehicleController(VehicleService vehicleService, LocationService locationService) {
	        this.vehicleService = vehicleService;
	        this.locationService = locationService;
	    }

	    @GetMapping("/{id}/locations")
	    public List<Location> getLocationsByVehicleId(@PathVariable int id) {
	        // First, check if the vehicle exists
	        if (!vehicleService.existsById(id)) {
	            throw new RuntimeException("Vehicle not found with id: " + id);
	        }

	        // Fetch location data for the specified vehicle
	        return locationService.getLocationsByVehicleId(id);
	    }

	//Get all the vehicles
	//localhost:8080/vehicles
	@GetMapping
	public List<Vehicle> getAllVehicles() {
		return vehicleService.getAllVehicles();
	}

	//Get a vehicle
	//localhost:8080/vehicles/1
	@GetMapping("/{id}")
	public Vehicle getVehicleById(@PathVariable int id) {
		return vehicleService.getVehicleById(id)
				.orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
	}

	//Registering a vehicle
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Vehicle registerVehicle(@RequestBody Vehicle vehicle) {
		return vehicleService.registerVehicle(vehicle);
	}

	//Updating a vehicle
	@PutMapping("/update/{id}")
	public Vehicle updateVehicle(@PathVariable int id, @RequestBody Vehicle updatedVehicle) {
		return vehicleService.updateVehicle(id, updatedVehicle);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeVehicle(@PathVariable int id) {
		vehicleService.removeVehicle(id);
	}
	
	@GetMapping("/vehicles/{id}/tracked")
	public ResponseEntity<?> isVehicleTracked(@PathVariable int id) {
	    boolean isTracked = locationService.isVehicleTracked(id);
	    if (isTracked) {
	        return ResponseEntity.ok("Vehicle is being tracked");
	    } else {
	        return ResponseEntity.ok("Vehicle is not being tracked");
	    }
	}

}
