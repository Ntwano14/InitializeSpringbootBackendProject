package com.numadic.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.numadic.restapi.entity.Vehicle;
import com.numadic.restapi.repository.VehicleRepository;

@RestController
public class VehicleController {

	@Autowired
	VehicleRepository repo;

	//Get all the vehicles
	//localhost:8080/vehicles
	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles()
	{
		List<Vehicle> vehicles = repo.findAll();
		return vehicles;
	}

	//Get all a vehicles
	//localhost:8080/vehicles/1
	@GetMapping("/vehicles/{id}")
	public Vehicle getVehicle(@PathVariable Long id)
	{
		Vehicle vehicle = repo.findById(id).get();
		return vehicle;
	}

	@PostMapping("/vehicle/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerVehicle(@RequestBody Vehicle vehicle)
	{
		repo.save(vehicle);
	}

	@PutMapping("/vehicle/update/{id}")
	public Vehicle updateVehicle(@PathVariable Long id)
	{
		Vehicle vehicle = repo.findById(id).get();
		vehicle.setOwnerName("Khensie");
		repo.save(vehicle);
		return vehicle;
	} 

	@DeleteMapping("/vehicle/delete/{id}")
	public void removeVehicle(@PathVariable Long id)
	{
		Vehicle vehicle = repo.findById(id).get();
		repo.delete(vehicle);
	}
}
