package com.excilys.cdb.binding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.ComputerDto;

public class ComputerMapper {
	
	
	/**
	 * Converts a page of computers into a list of computer DTOs.
	 * 
	 * @param computerPage
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
	 * @param list
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
	 * @param computer
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
			computerDto.setCompany(CompanyMapper.createDto(computer.getCompany()));
		}

		return computerDto;
	}
	
	/**
	 * Converts a computer Dto into a computer.
	 * 
	 * @param computerDto
	 *            ComputerDto to convert.
	 * @return Computer.
	 */
	public static Computer createObject(ComputerDto computerDto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Computer computer = new Computer();

		computer.setId(computerDto.getId());
		computer.setName(computerDto.getName());
		if (StringUtils.isNotBlank(computerDto.getIntroduced())) {
			computer.setIntroduced(LocalDate.parse(computerDto.getIntroduced().toString(), formatter));
		}
		if (StringUtils.isNotBlank(computerDto.getDiscontinued())) {
			computer.setDiscontinued(LocalDate.parse(computerDto.getDiscontinued().toString(), formatter));
		}
		if (computerDto.getCompany() != null) {
			computer.setCompany(CompanyMapper.createObject(computerDto.getCompany()));
		}

		return computer;
	}

}
