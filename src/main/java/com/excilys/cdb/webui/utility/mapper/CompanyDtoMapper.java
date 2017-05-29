package com.excilys.cdb.webui.utility.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.webui.dto.CompanyDto;

public class CompanyDtoMapper {	
	
	/**
	 * Converts a page of companies into a list of company DTOs.
	 * 
	 * @param Page
	 *            Page of companies to convert.
	 * @return Page of company DTOs.
	 */
	public static Page<CompanyDto> createDtoPage(Page<Company> companyPage) {
		Page<CompanyDto> companyDtoPage = new Page<>();

		companyDtoPage.setElementList(createDtoList(companyPage.getElementList()));
		companyDtoPage.setNumberOfElements(companyPage.getNumberOfElements());
		companyDtoPage.setNumberOfPages(companyPage.getNumberOfPages());
		companyDtoPage.setPageNumber(companyPage.getPageNumber());
		companyDtoPage.setPageSize(companyPage.getPageSize());
		companyDtoPage.setOrder(companyPage.getOrder());

		return companyDtoPage;
	}

	/**
	 * Converts a list of companies into a list of company DTOs.
	 * 
	 * @param List
	 *            List of companies to convert.
	 * @return List of company DTOs.
	 */
	public static List<CompanyDto> createDtoList(List<Company> list) {
		List<CompanyDto> dtoList = new ArrayList<>();

		for (Company c : list) {
			if (c != null) {
				dtoList.add(createDto(c));
			}
		}

		return dtoList;
	}
	
	/**
	 * Converts a company into a company DTO.
	 * 
	 * @param c
	 *            Company to convert.
	 * @return Company DTO.
	 */
	public static CompanyDto createDto(Company company) {
		CompanyDto companyDto = new CompanyDto();

		companyDto.setId(company.getId());
		companyDto.setName(company.getName());

		return companyDto;
	}
}