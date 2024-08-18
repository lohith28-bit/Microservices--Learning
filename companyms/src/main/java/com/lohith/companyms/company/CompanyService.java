package com.lohith.companyms.company;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public interface CompanyService {

	List<Company> getAllCompanies();

	boolean updateCompany(Company company, Long id);

	void createCompany(Company company);

	boolean deleteCompany(Long id);

	Company getcompanybyId(Long id);

}
