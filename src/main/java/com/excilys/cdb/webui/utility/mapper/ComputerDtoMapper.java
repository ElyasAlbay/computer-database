package com.excilys.cdb.webui.utility.mapper;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.webui.dto.ComputerDto;

public class ComputerDtoMapper extends AbstractDtoMapper<ComputerDto, Computer>{

	
	/**
	 * Class constructor.
	 */
	public ComputerDtoMapper() {
		
	}

	
	@Override
	public Page<ComputerDto> createDtoPage(Page<Computer> computerPage) {
		Page<ComputerDto> computerDtoPage = new Page<>();

		computerDtoPage.setElementList(createDtoList(computerPage.getElementList()));
		computerDtoPage.setNumberOfElements(computerPage.getNumberOfElements());
		computerDtoPage.setNumberOfPages(computerPage.getNumberOfPages());
		computerDtoPage.setPageNumber(computerPage.getPageNumber());
		computerDtoPage.setPageSize(computerPage.getPageSize());
		computerDtoPage.setOrder(computerPage.getOrder());

		return computerDtoPage;
	}

	@Override
	public ComputerDto createDto(Computer computer) {
		CompanyDtoMapper companyDtoMapper = new CompanyDtoMapper();
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
