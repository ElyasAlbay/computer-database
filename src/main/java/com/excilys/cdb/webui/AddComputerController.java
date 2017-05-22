package com.excilys.cdb.webui;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.CompanyServiceImpl;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ComputerServiceImpl;

/**
 * Servlet for addComputer web page.
 * @author Elyas Albay
 *
 */
public class AddComputerController extends HttpServlet {	
	private static final long serialVersionUID = 1921946279906681045L;
	private static final String view = "/WEB-INF/views/addComputer.jsp";

	private ComputerService computerService;
	private CompanyService companyService;
	
	
	/**
	 * Class constructor.
	 */
	public AddComputerController() {
		computerService = ComputerServiceImpl.INSTANCE;
		companyService = CompanyServiceImpl.INSTANCE;
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		String name;
		String introduced;
		String discontinued;
		String companyId;
		
		name = request.getParameter("computerName");
		introduced = request.getParameter("introduced");
		discontinued = request.getParameter("discontinued");
		companyId = request.getParameter("companyId");
		
		Computer computer = new Computer(0, name);
		//Company company = companyService.getById(companyId);
		computer.setIntroduced(LocalDate.parse(introduced));
		computer.setDiscontinued(LocalDate.parse(discontinued));
		//computer.setCompany(company);
		
		computerService.create(computer);
				
		
	}
	


}
