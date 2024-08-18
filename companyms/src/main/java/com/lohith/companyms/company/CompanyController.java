package com.lohith.companyms.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies() {
		return ResponseEntity.ok(companyService.getAllCompanies());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable long id) {
		if (companyService.updateCompany(company, id)) {
			return new ResponseEntity<>("Company details updated successfully", HttpStatus.ACCEPTED);
		}
		return ResponseEntity.status(403).body("Unable to update the Company.");
	}

	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body("Company added successfully");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
		if (companyService.deleteCompany(id)) {
			return ResponseEntity.ok("Company data deleted successfully");
		}

		return ResponseEntity.badRequest().body("Company not Found");
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getcompanybyId(@PathVariable Long id) {
		if (companyService.getcompanybyId(id) != null) {
			return ResponseEntity.ok(companyService.getcompanybyId(id));
		}
		return ResponseEntity.badRequest().body("Company Not Found");
	}

}
