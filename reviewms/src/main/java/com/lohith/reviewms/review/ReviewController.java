package com.lohith.reviewms.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lohith.reviewms.review.dto.ReviewWithCompanyDto;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping
	public ResponseEntity<List<ReviewWithCompanyDto>> getAllReviews(@RequestParam long companyId) {
		List<ReviewWithCompanyDto> reviews = reviewService.getAllReviews(companyId);
		if (!reviews.isEmpty()) {
			return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<String> addReview(@RequestParam long companyId, @RequestBody Review review) {
		reviewService.addReview(companyId, review);
		return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<?> getOneReview(@PathVariable long reviewId) {
		ReviewWithCompanyDto reviewWithCompanyDto = reviewService.getOneReview(reviewId);

		if (reviewWithCompanyDto != null) {
			return new ResponseEntity<>(reviewWithCompanyDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("{reviewId}")
	public ResponseEntity<String> updateReviewById(@PathVariable long reviewId,
			@RequestBody Review review) {
		if (Boolean.TRUE.equals(reviewService.updateReviewById(reviewId, review))) {
			return ResponseEntity.accepted().body("Succesfully updated the review");
		}
		return ResponseEntity.accepted().body("Review Not Found");
	}

	@DeleteMapping("{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable long reviewId) {
		if (Boolean.TRUE.equals(reviewService.deleteReviewById(reviewId))) {
			return new ResponseEntity<>("Review Deleted successfully", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Review Deleted successfully", HttpStatus.NOT_FOUND);

	}

}
