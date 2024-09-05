package com.lohith.companyms.company;

import java.util.*;

import org.springframework.stereotype.Service;

import com.lohith.companyms.company.dto.ReviewMessage;

@Service
public interface CompanyService {

	List<Company> getAllCompanies();

	boolean updateCompany(Company company, Long id);

	void createCompany(Company company);

	boolean deleteCompany(Long id);

	Company getcompanybyId(Long id);

	void updateCompanyRating(ReviewMessage reviewMessage);
}
