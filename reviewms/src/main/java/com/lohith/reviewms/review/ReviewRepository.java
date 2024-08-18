package com.lohith.reviewms.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	List<Review> findByCompanyID(Long companyId);
}
