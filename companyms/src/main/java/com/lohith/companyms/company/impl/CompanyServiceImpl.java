package com.lohith.companyms.company.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.lohith.companyms.company.Company;
import com.lohith.companyms.company.CompanyRepository;
import com.lohith.companyms.company.CompanyService;
import com.lohith.companyms.company.client.ReviewClient;
import com.lohith.companyms.company.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;

	private ReviewClient reviewClient;

	public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
		this.companyRepository = companyRepository;
		this.reviewClient = reviewClient;
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
	public void updateCompanyRating(ReviewMessage reviewMessage) {
		Double avgRating = reviewClient.getAvgForCompany(reviewMessage.getCompanyId());

		Company company = companyRepository.findById(reviewMessage.getCompanyId())
				.orElseThrow(() -> new NotFoundException("Company not found" + reviewMessage.getDescription()));

		company.setRating(avgRating);

		companyRepository.save(company);
	}

}
