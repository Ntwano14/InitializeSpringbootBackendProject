package com.numadic.restapi.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String model;
	private String make;
	@Column(name = "regNumber")
	private String registrationNumber;
	private String ownerName;
	private String ownerContactNumber;
	
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Location> locations;

	//Constructors
	public Vehicle() {
		
	}

	public Vehicle(String model, String make, String registrationNumber, String ownerName,
			String ownerContactNumber) {
		super(); 
		this.model = model;
		this.make = make;
		this.registrationNumber = registrationNumber;
		this.ownerName = ownerName;
		this.ownerContactNumber = ownerContactNumber;
	}

	//Getter and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerContactNumber() {
		return ownerContactNumber;
	}

	public void setOwnerContactNumber(String ownerContactNumber) {
		this.ownerContactNumber = ownerContactNumber;
	}

	// To String method
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", model=" + model + ", make=" + make + ", registrationNumber="
				+ registrationNumber + ", ownerName=" + ownerName + ", ownerContactNumber=" + ownerContactNumber + "]";
	}

}
