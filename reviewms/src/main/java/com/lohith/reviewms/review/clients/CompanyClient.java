package com.lohith.reviewms.review.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lohith.reviewms.review.external.Company;

@FeignClient(name = "COMPANY-SERVICE", url = "${company-service.url}")
public interface CompanyClient {

	@GetMapping("/companies/{companyId}")
	Company getCompany(@PathVariable long companyId);
}
