package com.lohith.jobms.job.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lohith.jobms.job.Job;
import com.lohith.jobms.job.JobRepository;
import com.lohith.jobms.job.JobService;
import com.lohith.jobms.job.dto.JobWithCompanyDTO;
import com.lohith.jobms.job.external.Company;
import com.lohith.jobms.job.external.Review;
import com.lohith.jobms.job.mapper.JobMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class JobServiceImpl implements JobService {
	JobRepository jobRepository;
	@Autowired
	private RestTemplate restTemplate;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	@CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallout")
	// @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallout")
	@RateLimiter(name = "companyBreaker")
	public List<JobWithCompanyDTO> findAll() {
		List<Job> jobs = jobRepository.findAll();

		return jobs.stream()
				.map(this::converttoDto)
				.collect(Collectors.toList());
	}

	public List<String> companyBreakerFallout(Exception e) {
		List<String> lst = new ArrayList<>();
		lst.add("Dummy");
		lst.add(e.toString());
		return lst;
	}

	@SuppressWarnings("null")
	private JobWithCompanyDTO converttoDto(Job job) {
		long companyId = job.getCompanyID();
		Company company = restTemplate.getForObject("http://COMPANY-SERVICE/companies/" + companyId, Company.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = restTemplate.getForObject("http://REVIEW-SERVICE/reviews?companyId=" + companyId,
				JsonNode.class);
		List<Review> reviewList = new ArrayList<>();
		for (JsonNode node : root) {
			JsonNode reviewNode = node.get("review");
			Review review = mapper.convertValue(reviewNode, Review.class);
			reviewList.add(review);
		}
		return new JobMapper().mapToJobWithCompanyDTO(job, company, reviewList);
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public JobWithCompanyDTO getJobById(Long id) {
		Job job = jobRepository.findById(id).orElse(null);
		if (job != null) {
			return converttoDto(job);
		}
		return null;

	}

	@Override
	public Boolean deleteJobByID(Long id) {

		if (jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateJob(Long id, Job updatedJob) {
		Optional<Job> jobOptional = jobRepository.findById(id);
		if (jobOptional.isPresent()) {
			Job job = jobOptional.get();
			job.setTitle(updatedJob.getTitle());
			job.setDescription(updatedJob.getDescription());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setLocation(updatedJob.getLocation());
			jobRepository.save(job);
			return true;
		}
		return false;
	}

}
