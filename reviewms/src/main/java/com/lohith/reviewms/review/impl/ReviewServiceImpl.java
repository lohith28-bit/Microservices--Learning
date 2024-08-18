package com.lohith.reviewms.review.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lohith.reviewms.review.Review;
import com.lohith.reviewms.review.ReviewRepository;
import com.lohith.reviewms.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Override
	public void addReview(Long companyId, Review review) {

		// Company company = new Company();
		// company.setID(companyId);
		// review.setCompany(company);
		// reviewRepository.save(review);

		if (companyId != null && review != null) {
			review.setCompanyID(companyId);
			reviewRepository.save(review);
		}
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		// List<Review> reviewsLoop = reviewRepository.findAll();
		// List<Review> resultReview = new ArrayList<>();

		// reviewsLoop.forEach(r -> {
		// if (r.getCompany().getID().equals(companyId)) {
		// resultReview.add(r);
		// }
		// });
		// return resultReview;

		return reviewRepository.findByCompanyID(companyId);
	}

	@Override
	public Review getOneReview(Long reviewId) {
		return reviewRepository.findById(reviewId).orElse(null);
	}

	@Override
	public Boolean updateReviewById(Long reviewId, Review review) {
		Optional<Review> optionalReview = reviewRepository.findById(reviewId);
		if (optionalReview.isPresent()) {
			Review updatedReview = optionalReview.get();
			updatedReview.setTitle(review.getTitle());
			updatedReview.setDescription(review.getDescription());
			updatedReview.setRating(review.getRating());
			reviewRepository.save(updatedReview);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteReviewById(Long reviewId) {
		Review review = reviewRepository.findById(reviewId).orElse(null);

		if (review != null) {
			reviewRepository.delete(review);
			return true;
		}
		return false;
	}
}
