package com.lohith.jobms.job.dto;

import com.lohith.jobms.job.Job;
import com.lohith.jobms.job.external.Company;

public class JobWithCompanyDTO {

	private Job job;
	private Company company;

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job value) {
		this.job = value;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company value) {
		this.company = value;
	}
}
