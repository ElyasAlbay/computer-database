package com.excilys.cdb.webui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.exceptions.ValidationException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.webui.dto.CompanyDto;
import com.excilys.cdb.webui.utility.Field;
import com.excilys.cdb.webui.utility.Validator;
import com.excilys.cdb.webui.utility.mapper.CompanyDtoMapper;

/**
 * Servlet for addComputer web page.
 * 
 * @author Elyas Albay
 *
 */
@Controller
@RequestMapping("/addComputer")
public class AddComputerController extends HttpServlet {
	private static final long serialVersionUID = 1921946279906681045L;
	private static final Logger LOG = LoggerFactory.getLogger(AddComputerController.class);
	
	private static final String VIEW = "/WEB-INF/views/addComputer";
	private static final String ATT_COMPANY_PG = "companyPage";
	private static final String DASHBOARD = "/dashboard";
	private static final String ERRORS = "errors";

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	

	public ModelAndView get() {
		LOG.info("Get request.");
		ModelAndView modelView = new ModelAndView(VIEW);
		Page<Company> companyPage = new Page<>();
		Page<CompanyDto> companyDtoPage = CompanyDtoMapper.createDtoPage(companyService.getAll(companyPage));
		
		modelView.addObject(ATT_COMPANY_PG, companyDtoPage);

		// Redirects to dashboard page with new parameters
		return modelView;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Computer computer = new Computer();
		Map<String, String> errors = new HashMap<String, String>();

		// Get form fields
		String name = request.getParameter(Field.COMPUTER_NAME);
		String introduced = request.getParameter(Field.INTRODUCED);
		String discontinued = request.getParameter(Field.DISCONTINUED);
		String companyId = request.getParameter(Field.COMPANY_ID);

		
		// Validate fields
		try {
			Validator.nameValidation(name);
		} catch (ValidationException e) {
			errors.put(Field.COMPUTER_NAME, e.getMessage());
		}
		try {
			Validator.introducedValidation(introduced);
		} catch (ValidationException e) {
			errors.put(Field.INTRODUCED, e.getMessage());
		}
		try {
			Validator.discontinuedValidation(discontinued, introduced);
		} catch (ValidationException e) {
			errors.put(Field.DISCONTINUED, e.getMessage());
		}
		try {
			Validator.companyIdValidation(companyId);
		} catch (ValidationException e) {
			errors.put(Field.COMPANY_ID, e.getMessage());
		}

		
		// Sends query if no errors, display error messages else
		if (errors.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			computer.setName(name);
			if (StringUtils.isNotBlank(introduced)) {
				computer.setIntroduced(LocalDate.parse(introduced, formatter));
			}
			if (StringUtils.isNotBlank(discontinued)) {
				computer.setDiscontinued(LocalDate.parse(discontinued, formatter));
			}
			if (StringUtils.isNotBlank(companyId) && !companyId.equals("0")) {
				computer.setCompany(companyService.getById(Integer.parseInt(companyId)));
			}
			
			computerService.create(computer);
			
			// Redirects to the dashboard once add query is completed
			response.sendRedirect(this.getServletContext().getContextPath() + DASHBOARD);
		} else {
			request.setAttribute(ERRORS, errors);
			
			// Displays jsp with errors
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}
	}
	
}
