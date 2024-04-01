package com.numadic.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String model;
	@Column
	private String make;
	@Column(name = "regNumber")
	private String registrationNumber;
	@Column
	private String ownerName;
	@Column
	private String ownerContactNumber;

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
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", model=" + model + ", make=" + make + ", registrationNumber="
				+ registrationNumber + ", ownerName=" + ownerName + ", ownerContactNumber=" + ownerContactNumber + "]";
	}

}
