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

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.CompanyServiceImpl;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ComputerServiceImpl;
import com.excilys.cdb.ui.Page;
import com.excilys.cdb.webui.dto.CompanyDto;
import com.excilys.cdb.webui.utility.CompanyDtoMapper;
import com.excilys.cdb.webui.utility.Field;

/**
 * Servlet for addComputer web page.
 * 
 * @author Elyas Albay
 *
 */
public class AddComputerController extends HttpServlet {
	private static final long serialVersionUID = 1921946279906681045L;
	private static final String VIEW = "/WEB-INF/views/addComputer.jsp";
	private static final String DASHBOARD = "/dashboard";
	private static final String ERRORS = "errors";

	private ComputerService computerService;
	private CompanyService companyService;
	private Page<Company> companyPage;
	private Page<CompanyDto> companyDtoPage;
	private CompanyDtoMapper companyDtoMapper;

	
	/**
	 * Class constructor.
	 */
	public AddComputerController() {
		computerService = ComputerServiceImpl.INSTANCE;
		companyService = CompanyServiceImpl.INSTANCE;

		companyPage = new Page<>();
		companyDtoPage = new Page<>();
		companyDtoMapper = new CompanyDtoMapper();

		companyDtoPage = companyDtoMapper.createDtoPage(companyService.listRequest(companyPage));
	}
	

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("companyPage", companyDtoPage);

		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Computer computer = new Computer(0, "placeholder");
		Map<String, String> errors = new HashMap<String, String>();

		// Get form fields
		String name = request.getParameter(Field.COMPUTER_NAME);
		String introduced = request.getParameter(Field.INTRODUCED);
		String discontinued = request.getParameter(Field.DISCONTINUED);
		String companyId = request.getParameter(Field.COMPANY_ID);

		
		// Validate fields
		try {
			nameValidation(name);
		} catch (Exception e) {
			errors.put(Field.COMPUTER_NAME, e.getMessage());
		}

		try {
			introducedValidation(introduced);
		} catch (Exception e) {
			errors.put(Field.INTRODUCED, e.getMessage());
		}

		try {
			discontinuedValidation(discontinued, introduced);
		} catch (Exception e) {
			errors.put(Field.DISCONTINUED, e.getMessage());
		}

		try {
			companyIdValidation(companyId);
		} catch (Exception e) {
			errors.put(Field.COMPANY_ID, e.getMessage());
		}

		
		// Sens query if no errors, display error messages else
		if (errors.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			computer.setName(name);
			
			if (StringUtils.isNotBlank(introduced)) {
				computer.setIntroduced(LocalDate.parse(introduced, formatter));
			}
			if (StringUtils.isNotBlank(discontinued)) {
				computer.setDiscontinued(LocalDate.parse(discontinued, formatter));
			}
			if (StringUtils.isNotBlank(companyId)) {
				computer.setCompany(companyService.getById(Integer.parseInt(companyId)));
			}
			
			computerService.create(computer);
		} else {
			request.setAttribute(ERRORS, errors);
			this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
		}

		// Redirects to the dashboard once add query is completed
		response.sendRedirect(this.getServletContext().getContextPath() + DASHBOARD);
	}
	
	
	/**
	 * Validates input name.
	 * 
	 * @param name
	 *            Computer name.
	 * @throws Exception
	 *             Thrown exception.
	 */
	private void nameValidation(String name) throws Exception {

		if (StringUtils.isBlank(name)) {
			throw new Exception("Name has to be specified.");
		}
	}

	/**
	 * Validates input introduced date.
	 * 
	 * @param introduced
	 *            Introduced date.
	 * @throws Exception
	 *             Thrown Exception.
	 */
	private void introducedValidation(String introduced) throws Exception {

		if (StringUtils.isNotBlank(introduced)) {
			if (!introduced.matches("\\d{4}-\\d{2}-\\d{2}")) {
				throw new Exception("Invalid introduced date format.");
			}
		}
	}

	/**
	 * Validates input discontinued date.
	 * 
	 * @param discontinued
	 *            Discontinued date.
	 * @param introduced
	 *            Introduced date.
	 * @throws Exception
	 *             Thrown exception.
	 */
	private void discontinuedValidation(String discontinued, String introduced) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (StringUtils.isNotBlank(discontinued)) {
			if (!discontinued.matches("\\d{4}-\\d{2}-\\d{2}")) {
				throw new Exception("Invalid discontinued date format.");
			} else if (introduced != null
					&& LocalDate.parse(introduced, formatter).isAfter(LocalDate.parse(discontinued, formatter))) {
				throw new Exception("Discontinued date should not be before introduced date.");
			}
		}
	}

	/**
	 * Validates input company id.
	 * 
	 * @param companyId
	 *            COmpany Id.
	 * @throws Exception
	 *             Thrown Exception.
	 */
	private void companyIdValidation(String companyId) throws Exception {
		int id = Integer.parseInt(companyId);
		
		if (id < 0) {
			throw new Exception("Company Id cannot be negative.");
		} else if (id != 0 && companyService.getById(id) == null) {
			throw new Exception("This id does not exist in the database." + companyId);
		}
	}

}
