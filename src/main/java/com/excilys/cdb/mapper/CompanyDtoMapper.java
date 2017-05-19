package com.excilys.cdb.mapper;

import com.excilys.cdb.dto.CompanyDto;
import com.excilys.cdb.model.Company;

public class CompanyDtoMapper extends DtoMapper<CompanyDto, Company> {

	/**
	 * Converts a company into a company DTO.
	 * 
	 * @param company
	 *            Company to convert.
	 * @return Company DTO.
	 */
	@Override
	public CompanyDto createDto(Company company) {
		CompanyDto companyDto = new CompanyDto();

		companyDto.setId(company.getId());
		companyDto.setName(company.getName());

		return companyDto;
	}
}
