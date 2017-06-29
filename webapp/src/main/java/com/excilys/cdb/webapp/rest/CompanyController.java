package com.excilys.cdb.webapp.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.cdb.binding.CompanyMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.CompanyDto;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.webapp.exceptions.RestExceptionHandler;

@Controller
@RequestMapping("/company")
public class CompanyController {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;
	
	
	
	@GetMapping(value = "/get/{id}")
	@ResponseBody
	public CompanyDto getById(@PathVariable long id) {
		LOG.info("Get by id request.");

		if (id < 0)
			RestExceptionHandler.throwInvalidParameterException("Invalid parameter.");

		CompanyDto company = CompanyMapper.createDto(companyService.getById(id));

		if (company == null)
			RestExceptionHandler.throwEntityNotFoundException("Company not found in database.");

		return company;
	}
	
	@GetMapping(value = "/page")
	@ResponseBody
	public List<CompanyDto> getPage() {
		LOG.info("Get page request.");

		Page<Company> companyPage = new Page<>();

		List<CompanyDto> companyList = CompanyMapper
				.createDtoList(companyService.getAll(companyPage).getElementList());

		return companyList;
	}

	@PostMapping(value = "/add")
	@ResponseBody
	public ResponseEntity<String> add(@Valid @RequestBody CompanyDto companyDto) {
		LOG.info("Add request.");

		Company company = CompanyMapper.createObject(companyDto);
			
		companyService.create(company);
		return new ResponseEntity<>("Company add susccessful. Id="+company.getId(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		LOG.info("Delete request.");

		if (id < 0)
			RestExceptionHandler.throwInvalidParameterException("Invalid parameter.");
		if (companyService.delete(id) <= 0)
			RestExceptionHandler.throwEntityNotFoundException("Company not found in database.");

		return new ResponseEntity<>("Company delete susccessful.", HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<String> update(@Valid @RequestBody CompanyDto companyDto) {
		LOG.info("Update request.");

		Company company = CompanyMapper.createObject(companyDto);

		companyService.update(company);
		return new ResponseEntity<>("Company update susccessful.", HttpStatus.OK);
	}
}
