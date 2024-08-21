package com.lohith.jobms.job.dto;

import java.util.List;

import com.lohith.jobms.job.external.Company;
import com.lohith.jobms.job.external.Review;

public class JobWithCompanyDTO {

  private Long id;
  private String title;
  private String description;
  private String minSalary;
  private String maxSalary;
  private String location;

  private Company company;

  private List<Review> reviews;

  public Company getCompany() {
    return this.company;
  }

  public void setCompany(Company value) {
    this.company = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String value) {
    this.title = value;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String value) {
    this.description = value;
  }

  public String getMinSalary() {
    return this.minSalary;
  }

  public void setMinSalary(String value) {
    this.minSalary = value;
  }

  public String getMaxSalary() {
    return this.maxSalary;
  }

  public void setMaxSalary(String value) {
    this.maxSalary = value;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String value) {
    this.location = value;
  }

  public List<Review> getReviews() {
    return this.reviews;
  }

  public void setReviews(List<Review> value) {
    this.reviews = value;
  }
}
