package com.lohith.jobms.job.external;

public class Review {

	private Long id;
	private String title;
	private String description;
	private Double rating;

	private Long companyID;

	// Default constructor
	public Review() {
	}

	public Review(Long id, String title, String description, double rating, Long companyID) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.companyID = companyID;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String value) {
		this.title = value;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public Double getRating() {
		return this.rating;
	}

	public void setRating(Double value) {
		this.rating = value;
	}

	public Long getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Long value) {
		this.companyID = value;
	}

}
