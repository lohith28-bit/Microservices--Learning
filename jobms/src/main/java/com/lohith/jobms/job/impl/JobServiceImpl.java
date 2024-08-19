package com.lohith.jobms.job.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lohith.jobms.job.Job;
import com.lohith.jobms.job.JobRepository;
import com.lohith.jobms.job.JobService;
import com.lohith.jobms.job.dto.JobWithCompanyDTO;
import com.lohith.jobms.job.external.Company;

@Service
public class JobServiceImpl implements JobService {
	JobRepository jobRepository;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public List<JobWithCompanyDTO> findAll() {
		List<Job> jobs = jobRepository.findAll();

		return jobs.stream()
				.map(this::converttoDto)
				.collect(Collectors.toList());
	}

	private JobWithCompanyDTO converttoDto(Job job) {
		long companyId = job.getCompanyID();
		RestTemplate restTemplate = new RestTemplate();
		Company company = restTemplate.getForObject("http://localhost:8081/companies/" + companyId, Company.class);
		JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
		jobWithCompanyDTO.setJob(job);
		jobWithCompanyDTO.setCompany(company);
		return jobWithCompanyDTO;

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
