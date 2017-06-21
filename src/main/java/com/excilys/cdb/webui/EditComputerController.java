package com.excilys.cdb.webui;

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

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.CompanyDto;
import com.excilys.cdb.model.dto.ComputerDto;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.webui.utility.Field;
import com.excilys.cdb.webui.utility.mapper.CompanyDtoMapper;
import com.excilys.cdb.webui.utility.mapper.ComputerDtoMapper;

/**
 * Controller for updateComputer web page.
 * 
 * @author Elyas Albay
 *
 */
@Controller
@RequestMapping("/editComputer")
public class EditComputerController {
	private static final Logger LOG = LoggerFactory.getLogger(EditComputerController.class);

	private static final String VIEW = "/WEB-INF/views/editComputer";
	private static final String DASHBOARD = "/dashboard";
	private static final String ATT_COMPUTER = "computer";
	private static final String ATT_COMPANY_PG = "companyPage";
	private static final String COMPUTER_ID = "computer_id";
	private static final String ERRORS = "errors";

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ModelAndView get(@RequestParam Map<String, String> params) {
		LOG.info("Get request");

		ModelAndView modelView = new ModelAndView(VIEW);
		Page<CompanyDto> companyDtoPage = CompanyDtoMapper.createDtoPage(companyService.getAll(new Page<Company>()));
		ComputerDto computerDto = null;

		String computerId = params.get(COMPUTER_ID);

		if (StringUtils.isNotBlank(computerId) && Integer.parseInt(computerId) > 0) {
				computerDto = ComputerDtoMapper.createDto(computerService.getById(Long.parseLong(computerId)));
		}

		modelView.addObject(ATT_COMPANY_PG, companyDtoPage);
		modelView.addObject(ATT_COMPUTER, computerDto);

		return modelView;
	}

	@PostMapping
	public ModelAndView post(@RequestParam Map<String, String> params, @Valid @ModelAttribute ComputerDto computerDto,
			BindingResult bindingResult) {
		LOG.info("Post request.");
		
		ModelAndView modelView = new ModelAndView(VIEW);

		// Get form fields
		String id = params.get(Field.COMPUTER_ID);
		String name = params.get(Field.COMPUTER_NAME);
		String introduced = params.get(Field.INTRODUCED);
		String discontinued = params.get(Field.DISCONTINUED);
		String companyId = params.get(Field.COMPANY_ID);

		
		// Sends query if no errors and redirects to dashboard, display error
				// messages else
		if (!bindingResult.hasErrors()) {
			LOG.info("Valid request, adding computer to database.");
			
			computerDto.setId(Long.parseLong(id));
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
			computerService.update(computer);

			modelView.setViewName("redirect:" + DASHBOARD);
		} else {
			LOG.info("Invalid request, displaying error messages.");
			Map<String, String> errors = new HashMap<String, String>();
			Page<CompanyDto> companyDtoPage = CompanyDtoMapper.createDtoPage(companyService.getAll(new Page<Company>()));			
			
			if (bindingResult.getFieldError(Field.COMPUTER_NAME) != null)
				errors.put(Field.COMPUTER_NAME, bindingResult.getFieldError(Field.COMPUTER_NAME).getDefaultMessage());
			if (bindingResult.getFieldError(Field.INTRODUCED) != null)
				errors.put(Field.INTRODUCED, bindingResult.getFieldError(Field.INTRODUCED).getDefaultMessage());
			if (bindingResult.getFieldError(Field.DISCONTINUED) != null)
				errors.put(Field.DISCONTINUED, bindingResult.getFieldError(Field.DISCONTINUED).getDefaultMessage());
			if (bindingResult.getFieldError(Field.COMPUTER_ID) != null)
				errors.put(Field.COMPANY_ID, bindingResult.getFieldError(Field.COMPANY_ID).getDefaultMessage());
			
			modelView.addObject(ATT_COMPUTER, computerDto);
			modelView.addObject(ATT_COMPANY_PG, companyDtoPage);
			modelView.addObject(ERRORS, errors);
		}

		return modelView;
	}

}
