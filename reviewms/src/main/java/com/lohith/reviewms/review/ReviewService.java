package com.lohith.reviewms.review;

import java.util.List;

import com.lohith.reviewms.review.dto.ReviewWithCompanyDto;

public interface ReviewService {

	List<ReviewWithCompanyDto> getAllReviews(Long companyId);

	void addReview(Long companyId, Review review);

	ReviewWithCompanyDto getOneReview(Long reviewId);

	Boolean updateReviewById(Long reviewId, Review review);

	Boolean deleteReviewById(Long reviewId);

	Double getAverageRating(Long compantId);

}