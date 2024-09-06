package com.lohith.reviewms.review.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lohith.reviewms.review.Review;
import com.lohith.reviewms.review.ReviewRepository;
import com.lohith.reviewms.review.ReviewService;
import com.lohith.reviewms.review.clients.CompanyClient;
import com.lohith.reviewms.review.dto.ReviewWithCompanyDto;
import com.lohith.reviewms.review.external.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReviewServiceImpl implements ReviewService {

	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

	private ReviewRepository reviewRepository;

	// private RestTemplate restTemplate;

	private CompanyClient companyClient;

	public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyClient companyClient) {
		this.reviewRepository = reviewRepository;
		this.companyClient = companyClient;
	}

	@Override
	public void addReview(Long companyId, Review review) {
		if (companyId != null && review != null) {
			review.setCompanyID(companyId);
			reviewRepository.save(review);
		}
	}

	@Override
	public List<ReviewWithCompanyDto> getAllReviews(Long companyId) {
		List<Review> reviewList = reviewRepository.findByCompanyID(companyId);
		return reviewList.stream()
				.map(review -> converttoDto(review, companyId))
				.collect(Collectors.toList());
	}

	private ReviewWithCompanyDto converttoDto(Review review, Long companyId) {
		// Company company =
		// restTemplate.getForObject("http://COMPANY-SERVICE/companies/" + companyId,
		// Company.class);

		Company company = companyClient.getCompany(companyId);
		if (company == null) {
			logger.error("Failed to fetch company details for companyId: {}", companyId);
		}
		ReviewWithCompanyDto reviewWithCompanyDto = new ReviewWithCompanyDto();
		reviewWithCompanyDto.setReview(review);
		reviewWithCompanyDto.setCompany(company);

		return reviewWithCompanyDto;
	}

	@Override
	public ReviewWithCompanyDto getOneReview(Long reviewId) {
		Review review = reviewRepository.findById(reviewId).orElse(null);
		if (review != null) {
			long companyId = review.getCompanyID();
			return converttoDto(review, companyId);
		}
		return null;
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

	@Override
	public Double getAverageRating(Long companyId) {
		List<Review> reviewList = reviewRepository.findByCompanyID(companyId);

		return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
	}
}
