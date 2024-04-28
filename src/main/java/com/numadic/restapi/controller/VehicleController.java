// VehicleController.java
package com.numadic.restapi.controller;

import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.exception.ResourceNotFoundException;
import com.numadic.restapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id) {
        Vehicle vehicle = vehicleService.getVehicleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vehicle> registerVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.registerVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @Valid @RequestBody Vehicle updatedVehicle) {
        Optional<Vehicle> optionalVehicle = vehicleService.getVehicleById(id);
        if (optionalVehicle.isEmpty()) {
            throw new ResourceNotFoundException("Vehicle not found with id: " + id);
        }
        Vehicle vehicle = vehicleService.updateVehicle(id, updatedVehicle);
        return ResponseEntity.ok(vehicle);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeVehicle(@PathVariable Integer id) {
        Optional<Vehicle> optionalVehicle = vehicleService.getVehicleById(id);
        if (optionalVehicle.isEmpty()) {
            throw new ResourceNotFoundException("Vehicle not found with id: " + id);
        }
        vehicleService.removeVehicle(id);
    }

}
