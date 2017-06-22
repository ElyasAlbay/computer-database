package com.excilys.cdb.webapp;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.binding.CompanyDtoMapper;
import com.excilys.cdb.binding.ComputerDtoMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.CompanyDto;
import com.excilys.cdb.model.dto.ComputerDto;
import com.excilys.cdb.model.util.Field;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Controller for addComputer web page.
 * 
 * @author Elyas Albay
 *
 */
@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
	private static final Logger LOG = LoggerFactory.getLogger(AddComputerController.class);

	private static final String VIEW = "/WEB-INF/views/addComputer";
	private static final String DASHBOARD = "/dashboard";
	private static final String ATT_COMPANY_PG = "companyPage";
	private static final String ERRORS = "errors";
	private static final String dateInvalidMessage = "Introduced date cannot be anterior to discontinued date.";

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ModelAndView get() {
		LOG.info("Get request.");

		ModelAndView modelView = new ModelAndView(VIEW);
		Page<CompanyDto> companyDtoPage = CompanyDtoMapper.createDtoPage(companyService.getAll(new Page<Company>()));

		modelView.addObject(ATT_COMPANY_PG, companyDtoPage);

		// Redirects to dashboard page with new parameters
		return modelView;
	}

	@PostMapping
	public ModelAndView doPost(@RequestParam Map<String, String> params, @Valid @ModelAttribute ComputerDto computerDto,
			BindingResult bindingResult) {
		LOG.info("Post request.");

		ModelAndView modelView = new ModelAndView(VIEW);
		Map<String, String> errors = new HashMap<String, String>();
		Page<CompanyDto> companyDtoPage = CompanyDtoMapper.createDtoPage(companyService.getAll(new Page<Company>()));

		// Get form fields
		String name = params.get(Field.COMPUTER_NAME);
		String introduced = params.get(Field.INTRODUCED);
		String discontinued = params.get(Field.DISCONTINUED);
		String companyId = params.get(Field.COMPANY_ID);

		// Sends query if no errors and redirects to dashboard, display error
		// messages else
		if (!bindingResult.hasErrors()) {
			
			computerDto.setName(name);
			computerDto.setIntroduced(introduced);
			computerDto.setDiscontinued(discontinued);
			if (StringUtils.isNotBlank(companyId) && !companyId.equals("0")) {
				CompanyDto companyDto = CompanyDtoMapper.createDto(companyService.getById(Long.parseLong(companyId)));
				computerDto.setCompany(companyDto);
			} else {
				computerDto.setCompany(null);
			}

			Computer computer = ComputerDtoMapper.createObject(computerDto);
			if (computer.getIntroduced() != null && computer.getDiscontinued() != null
					&& computer.getDiscontinued().isBefore(computer.getIntroduced())) {
				errors.put(Field.INTRODUCED, dateInvalidMessage);
				modelView.addObject(ERRORS, errors);
				modelView.addObject(ATT_COMPANY_PG, companyDtoPage);
				
			} else {
				computerService.create(computer);
				modelView.setViewName("redirect:" + DASHBOARD);
			}
			
		} else {
			
			if (bindingResult.getFieldError(Field.COMPUTER_NAME) != null)
				errors.put(Field.COMPUTER_NAME, bindingResult.getFieldError(Field.COMPUTER_NAME).getDefaultMessage());
			if (bindingResult.getFieldError(Field.INTRODUCED) != null)
				errors.put(Field.INTRODUCED, bindingResult.getFieldError(Field.INTRODUCED).getDefaultMessage());
			if (bindingResult.getFieldError(Field.DISCONTINUED) != null)
				errors.put(Field.DISCONTINUED, bindingResult.getFieldError(Field.DISCONTINUED).getDefaultMessage());
			if (bindingResult.getFieldError(Field.COMPUTER_ID) != null)
				errors.put(Field.COMPANY_ID, bindingResult.getFieldError(Field.COMPANY_ID).getDefaultMessage());

			modelView.addObject(ATT_COMPANY_PG, companyDtoPage);
			modelView.addObject(ERRORS, errors);
		}

		return modelView;
	}

}
