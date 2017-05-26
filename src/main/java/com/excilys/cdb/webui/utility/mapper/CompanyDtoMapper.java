package com.excilys.cdb.webui.utility.mapper;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.webui.dto.CompanyDto;

public class CompanyDtoMapper extends AbstractDtoMapper<CompanyDto, Company> {
	
	/**
	 * Class constructor.
	 */
	public CompanyDtoMapper() {

	}
	
	
	@Override
	public Page<CompanyDto> createDtoPage(Page<Company> companyPage) {
		Page<CompanyDto> companyDtoPage = new Page<>();

		companyDtoPage.setElementList(createDtoList(companyPage.getElementList()));
		companyDtoPage.setNumberOfElements(companyPage.getNumberOfElements());
		companyDtoPage.setNumberOfPages(companyPage.getNumberOfPages());
		companyDtoPage.setPageNumber(companyPage.getPageNumber());
		companyDtoPage.setPageSize(companyPage.getPageSize());
		companyDtoPage.setOrder(companyPage.getOrder());

		return companyDtoPage;
	}

	@Override
	public CompanyDto createDto(Company company) {
		CompanyDto companyDto = new CompanyDto();

		companyDto.setId(company.getId());
		companyDto.setName(company.getName());

		return companyDto;
	}
}
