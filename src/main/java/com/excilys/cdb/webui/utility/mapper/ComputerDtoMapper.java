package com.excilys.cdb.webui.utility.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.webui.dto.ComputerDto;

public class ComputerDtoMapper {
	
	
	/**
	 * Converts a page of computers into a list of computer DTOs.
	 * 
	 * @param Page
	 *            Page of computers to convert.
	 * @return Page of computer DTOs.
	 */
	public static Page<ComputerDto> createDtoPage(Page<Computer> computerPage) {
		Page<ComputerDto> computerDtoPage = new Page<>();

		computerDtoPage.setElementList(createDtoList(computerPage.getElementList()));
		computerDtoPage.setNumberOfElements(computerPage.getNumberOfElements());
		computerDtoPage.setNumberOfPages(computerPage.getNumberOfPages());
		computerDtoPage.setPageNumber(computerPage.getPageNumber());
		computerDtoPage.setPageSize(computerPage.getPageSize());
		computerDtoPage.setOrder(computerPage.getOrder());

		return computerDtoPage;
	}
	
	/**
	 * Converts a list of computers into a list of computer DTOs.
	 * 
	 * @param List
	 *            List of computers to convert.
	 * @return List of computer DTOs.
	 */
	public static List<ComputerDto> createDtoList(List<Computer> list) {
		List<ComputerDto> dtoList = new ArrayList<>();

		for (Computer c : list) {
			if (c != null) {
				dtoList.add(createDto(c));
			}
		}

		return dtoList;
	}

	/**
	 * Converts a computer into a computer DTO.
	 * 
	 * @param c
	 *            Computer to convert.
	 * @return Computer DTO.
	 */
	public static ComputerDto createDto(Computer computer) {
		ComputerDto computerDto = new ComputerDto();

		computerDto.setId(computer.getId());
		computerDto.setName(computer.getName());
		if (computer.getIntroduced() != null) {
			computerDto.setIntroduced(computer.getIntroduced().toString());
		}
		if (computer.getDiscontinued() != null) {
			computerDto.setDiscontinued(computer.getDiscontinued().toString());
		}
		if (computer.getCompany() != null) {
			computerDto.setCompany(CompanyDtoMapper.createDto(computer.getCompany()));
		}

		return computerDto;
	}

}
