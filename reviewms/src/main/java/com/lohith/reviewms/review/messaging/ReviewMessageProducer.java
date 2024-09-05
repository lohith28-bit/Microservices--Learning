package com.lohith.reviewms.review.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.lohith.reviewms.review.Review;
import com.lohith.reviewms.review.dto.ReviewMessage;

@Service
public class ReviewMessageProducer {

	private final RabbitTemplate rabbitTemplate;

	ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(Review review) {
		ReviewMessage reviewMessage = new ReviewMessage();
		reviewMessage.setId(review.getId());
		reviewMessage.setTitle(review.getTitle());
		reviewMessage.setCompantId(review.getCompanyID());
		reviewMessage.setDescription(review.getDescription());
		reviewMessage.setRating(review.getRating());

		rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);
	}
}
