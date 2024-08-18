package com.lohith.jobms.job.impl;

import java.util.*;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.lohith.jobms.job.Job;
import com.lohith.jobms.job.JobRepository;
import com.lohith.jobms.job.JobService;

@Service
public class JobServiceImpl implements JobService {
	// private List<Job> jobs = new ArrayList<>();
	// private Long nextId = 1L;

	JobRepository jobRepository;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> findAll() {
		// return jobs;

		return jobRepository.findAll();
	}

	@Override
	public void createJob(Job job) {
		// job.setID(nextId++);
		// jobs.add(job);

		jobRepository.save(job);
	}

	@Override
	public Job getJobById(Long id) {
		// for(Job job : jobs){
		// if(job.getID().equals(id)) return job;
		// }
		// return null;

		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public Boolean deleteJobByID(Long id) {
		// Iterator<Job> iterator = jobs.iterator();
		// while(iterator.hasNext()){
		// Job job = iterator.next();
		// if(job.getID().equals(id)) {
		// iterator.remove();
		// return true;
		// }
		// }
		// return false;

		if (jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateJob(Long id, Job updatedJob) {
		// for(Job job : jobs){
		// if(job.getID().equals(id)){
		// job.setTitle(updatedJob.getTitle());
		// job.setDescripion(updatedJob.getDescripion());
		// job.setMaxSalary(updatedJob.getMaxSalary());
		// job.setMinSalary(updatedJob.getMinSalary());
		// job.setLocation(updatedJob.getLocation());
		// return true;
		// }
		// }
		// return false;

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
