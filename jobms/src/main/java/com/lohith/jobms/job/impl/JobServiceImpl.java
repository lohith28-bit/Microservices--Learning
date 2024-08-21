package com.lohith.jobms.job.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lohith.jobms.job.Job;
import com.lohith.jobms.job.JobRepository;
import com.lohith.jobms.job.JobService;
import com.lohith.jobms.job.dto.JobWithCompanyDTO;
import com.lohith.jobms.job.external.Company;
import com.lohith.jobms.job.mapper.JobMapper;

@Service
public class JobServiceImpl implements JobService {
	JobRepository jobRepository;
	@Autowired
	private RestTemplate restTemplate;

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
		Company company = restTemplate.getForObject("http://COMPANY-SERVICE/companies/" + companyId, Company.class);
		return new JobMapper().mapToJobWithCompanyDTO(job, company);
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
