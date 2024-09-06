package com.lohith.companyms.company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double rating;

	public Company() {
	}

	public Company(Long id, String name, String description, Double rating) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rating = rating;
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return this.rating;
	}

	public void setRating(Double value) {
		this.rating = value;
	}
}
