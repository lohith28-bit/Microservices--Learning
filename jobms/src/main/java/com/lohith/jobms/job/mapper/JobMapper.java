package com.lohith.jobms.job.mapper;

import com.lohith.jobms.job.Job;
import com.lohith.jobms.job.dto.JobWithCompanyDTO;
import com.lohith.jobms.job.external.Company;

public class JobMapper {
	public JobWithCompanyDTO mapToJobWithCompanyDTO(Job job, Company company) {
		JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
		jobWithCompanyDTO.setCompany(company);
		jobWithCompanyDTO.setDescription(job.getDescription());
		jobWithCompanyDTO.setId(job.getID());
		jobWithCompanyDTO.setLocation(job.getLocation());
		jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
		jobWithCompanyDTO.setMinSalary(job.getMinSalary());
		jobWithCompanyDTO.setTitle(job.getTitle());
		return jobWithCompanyDTO;
	}
}
