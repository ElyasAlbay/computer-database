package com.excilys.cdb.dto;

/**
 * Dto for Computer object.
 * 
 * @author Elyas Albay
 *
 */
public class ComputerDto {
	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyDto companyDto;

	
	/**
	 * Class constructor.
	 */
	public ComputerDto() {
		companyDto = new CompanyDto();

	}

	
	/* Getters and setters */
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		if (id >= 0) {
			this.id = id;
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public CompanyDto getCompany() {
		return companyDto;
	}

	public void setCompany(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}
}
