package com.excilys.cdb.webui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ComputerServiceImpl;
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
	private static final String DASHBOARD = "/dashboard";
	private static final String ATT_COMPUTER_PG = "computerPage";
	private static final String PAGE_NUMBER = "page_number";
	private static final String PAGE_SIZE = "page_size";
	private static final String SEARCH = "search";
	private static final String SELECTION = "selection";

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
			int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE));
			computerPage.setPageSize(pageSize);
		}
		if (request.getParameter("order") != null) {
			computerPage.setOrder(request.getParameter("order"));
		}
		
		// Creates a new computerDto page from computer page.
		if (request.getParameter(SEARCH) != null) {
			request.setAttribute(SEARCH, request.getParameter(SEARCH));
			computerDtoPage = computerDtoMapper.createDtoPage(computerService.searchByName((computerPage), request.getParameter(SEARCH)));
		} else {
			computerDtoPage = computerDtoMapper.createDtoPage(computerService.listRequest(computerPage));
		}
		request.setAttribute(ATT_COMPUTER_PG, computerDtoPage);
		
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get computer id selection from POST request
		String selection = request.getParameter(SELECTION);   
		String linkParams = "";
		
		// Get page parameters
		if (request.getParameter(PAGE_NUMBER) != null) {
			int pageNb = Integer.parseInt(request.getParameter(PAGE_NUMBER));
			linkParams += "?"+PAGE_NUMBER+"="+pageNb;
		}
		if (request.getParameter(PAGE_SIZE) != null) {
			int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE));
			linkParams += "&"+PAGE_SIZE+"="+pageSize;
		}
		 
		// Delete computers from received identifiers
		for (String id: selection.split(",")) {
				computerService.delete(Integer.parseInt(id));
		}
		
		// Redirects to the GET request.
		response.sendRedirect(this.getServletContext().getContextPath() + DASHBOARD + linkParams);
	}
}
