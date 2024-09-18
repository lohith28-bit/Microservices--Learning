package com.lohith.reviewms.review.dto;

public class ReviewMessage {

	private Long id;
	private String title;
	private String description;
	private Double rating;
	private Long companyId;

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

	public double getRating() {
		return this.rating;
	}

	public void setRating(double value) {
		this.rating = value;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long value) {
		this.companyId = value;
	}
}
