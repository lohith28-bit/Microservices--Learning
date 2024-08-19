package com.lohith.reviewms.review.dto;

import com.lohith.reviewms.review.Review;
import com.lohith.reviewms.review.external.Company;

public class ReviewWithCompanyDto {
	private Review review;
	private Company company;

	public Review getReview() {
		return this.review;
	}

	public void setReview(Review value) {
		this.review = value;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company value) {
		this.company = value;
	}
}