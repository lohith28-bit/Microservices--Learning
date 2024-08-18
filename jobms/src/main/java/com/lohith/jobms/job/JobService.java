package com.lohith.jobms.job;
import java.util.List;

public interface JobService {
	List<Job> findAll();

	void createJob(Job job);

	Job getJobById(Long id);

	Boolean deleteJobByID(Long id);

	Boolean updateJob(Long id, Job job);
}

