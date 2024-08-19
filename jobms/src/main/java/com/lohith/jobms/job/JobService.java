package com.lohith.jobms.job;

import java.util.List;

import com.lohith.jobms.job.dto.JobWithCompanyDTO;

public interface JobService {
	List<JobWithCompanyDTO> findAll();

	void createJob(Job job);

	JobWithCompanyDTO getJobById(Long id);

	Boolean deleteJobByID(Long id);

	Boolean updateJob(Long id, Job job);
}
