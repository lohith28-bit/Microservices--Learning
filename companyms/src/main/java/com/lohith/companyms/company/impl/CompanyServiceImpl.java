package com.lohith.companyms.company.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.lohith.companyms.company.Company;
import com.lohith.companyms.company.CompanyRepository;
import com.lohith.companyms.company.CompanyService;
import com.lohith.companyms.company.dto.ReviewMessage;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public boolean updateCompany(Company company, Long id) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		if (companyOptional.isPresent()) {
			Company companyToUpdate = companyOptional.get();
			companyToUpdate.setName(company.getName());
			companyToUpdate.setDescription(company.getDescription());
			companyRepository.save(companyToUpdate);
			return true;
		}
		return false;
	}

	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public boolean deleteCompany(Long id) {
		if (companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Company getcompanybyId(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

	@Override
	public void updateCompanyRating(ReviewMessage reviewMessage){
		System.out.println("Description "+reviewMessage.getDescription());

	}

}
