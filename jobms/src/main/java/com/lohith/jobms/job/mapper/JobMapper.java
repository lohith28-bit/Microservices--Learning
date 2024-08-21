package com.lohith.jobms.job.mapper;

import java.util.List;

import com.lohith.jobms.job.Job;
import com.lohith.jobms.job.dto.JobWithCompanyDTO;
import com.lohith.jobms.job.external.Company;
import com.lohith.jobms.job.external.Review;

public class JobMapper {
	public JobWithCompanyDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviewList) {
		JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
		jobWithCompanyDTO.setCompany(company);
		jobWithCompanyDTO.setReviews(reviewList);
		jobWithCompanyDTO.setDescription(job.getDescription());
		jobWithCompanyDTO.setId(job.getID());
		jobWithCompanyDTO.setLocation(job.getLocation());
		jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
		jobWithCompanyDTO.setMinSalary(job.getMinSalary());
		jobWithCompanyDTO.setTitle(job.getTitle());
		return jobWithCompanyDTO;
	}
}
