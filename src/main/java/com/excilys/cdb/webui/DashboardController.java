package com.excilys.cdb.webui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ComputerServiceImpl;
import com.excilys.cdb.ui.Page;
import com.excilys.cdb.webui.dto.ComputerDto;
import com.excilys.cdb.webui.utility.mapper.ComputerDtoMapper;

/**
 * Servlet for dashboard web page.
 * 
 * @author Elyas Albay
 *
 */
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 6646407915881068151L;
	
	private static final String VIEW = "/WEB-INF/views/dashboard.jsp";
	private static final String ATT_COMPUTER_PG = "computerPage";
	private static final String PAGE_NUMBER = "page_number";
	private static final String PAGE_SIZE = "page_size";

	private ComputerService computerService;
	private ComputerDtoMapper computerDtoMapper;

	
	/**
	 * Class constructor.
	 */
	public DashboardController() {
		computerService = ComputerServiceImpl.INSTANCE;
		
		computerDtoMapper = new ComputerDtoMapper();
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page<Computer> computerPage = new Page<>();
		Page<ComputerDto> computerDtoPage = new Page<>();

		// Get parameters from GET request if exists
		if (request.getParameter(PAGE_NUMBER) != null) {
			computerPage.setPageNumber(Integer.parseInt(request.getParameter(PAGE_NUMBER)));
		}
		if (request.getParameter(PAGE_SIZE) != null) {
			computerPage.setPageSize(Integer.parseInt(request.getParameter(PAGE_SIZE)));
		}
		
		// Creates a new computerDto page from computer page.
		computerDtoPage = computerDtoMapper.createDtoPage(computerService.listRequest(computerPage));
		request.setAttribute(ATT_COMPUTER_PG, computerDtoPage);
		
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

}
