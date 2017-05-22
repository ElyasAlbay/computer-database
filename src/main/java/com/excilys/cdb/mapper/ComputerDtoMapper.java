package com.excilys.cdb.mapper;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.ui.Page;

public class ComputerDtoMapper extends DtoMapper<ComputerDto, Computer>{
	private CompanyDtoMapper companyDtoMapper;

	
	/**
	 * Class constructor.
	 */
	public ComputerDtoMapper() {
		this(new CompanyDtoMapper());
	}
	
	/**
	 * Class constructor.
	 */
	public ComputerDtoMapper(CompanyDtoMapper companyDtoMapper) {
		this.companyDtoMapper = companyDtoMapper;
	}

	
	/**
	 * Converts a page of computers into a page of computer DTOs.
	 * 
	 * @param computerPage
	 *            Page of computers to convert.
	 * @return Page of computer DTOs.
	 */
	public Page<ComputerDto> createDtoPage(Page<Computer> computerPage) {
		Page<ComputerDto> computerDtoPage = new Page<>();

		computerDtoPage.setElementList(createDtoList(computerPage.getElementList()));
		computerDtoPage.setNumberOfElements(computerPage.getNumberOfElements());
		computerDtoPage.setNumberOfPages(computerPage.getNumberOfPages());
		computerDtoPage.setPageNumber(computerPage.getPageNumber());
		computerDtoPage.setPageSize(computerPage.getPageSize());

		return computerDtoPage;
	}

	/**
	 * Converts a computer into a computer DTOs.
	 * 
	 * @param computer
	 *            Computer to convert.
	 * @return Computer DTO.
	 */
	@Override
	public ComputerDto createDto(Computer computer) {
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
			computerDto.setCompany(companyDtoMapper.createDto(computer.getCompany()));
		}

		return computerDto;
	}

}
