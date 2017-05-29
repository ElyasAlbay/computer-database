package com.excilys.cdb.webui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ComputerServiceImpl;
import com.excilys.cdb.webui.dto.ComputerDto;
import com.excilys.cdb.webui.utility.Field;
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
	private static final String ORDER = "order";
	private static final String SEARCH = "search";
	private static final String SELECTION = "selection";

	private ComputerService computerService;

	
	/**
	 * Class constructor.
	 */
	public DashboardController() {
		computerService = ComputerServiceImpl.INSTANCE;
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page<Computer> computerPage = new Page<>();
		Page<ComputerDto> computerDtoPage = new Page<>();

		String pageNumber = request.getParameter(PAGE_NUMBER);
		String pageSize = request.getParameter(PAGE_SIZE);
		String order = request.getParameter(ORDER);
		String search = request.getParameter(SEARCH);
		
		
		// Get parameters from GET request if exists
		if (StringUtils.isNotBlank(pageNumber)) {
			int number = Integer.parseInt(pageNumber);
			
			if (number > 0) {
				computerPage.setPageNumber(number);
			}
		}
		if (StringUtils.isNotBlank(pageSize)) {
			int size = Integer.parseInt(pageSize);
			
			if(size == 10 || size == 50 || size == 100) {
				computerPage.setPageSize(size);
			}
		}
		if (StringUtils.isNotBlank(order)) {
			if (Field.contains(order)) {
				computerPage.setOrder(order);
			}
		}
		
		
		// Creates a new computerDto page from computer page.
		if (StringUtils.isNotBlank(search)) {
			request.setAttribute(SEARCH, search);
			computerDtoPage = ComputerDtoMapper.createDtoPage(computerService.searchByName(computerPage, search));
		} else {
			computerDtoPage = ComputerDtoMapper.createDtoPage(computerService.getAll(computerPage));
		}
		
		request.setAttribute(ATT_COMPUTER_PG, computerDtoPage);
		
		this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get computer id selection from POST request
		String selection = request.getParameter(SELECTION);  
		String pageNumber = request.getParameter(PAGE_NUMBER);
		String pageSize = request.getParameter(PAGE_SIZE);
		String linkParams = "";
		
		// Get page parameters
		if (StringUtils.isNotBlank(pageNumber)) {
			linkParams += "?"+PAGE_NUMBER+"="+pageNumber;
		}
		if (StringUtils.isNotBlank(pageSize)) {
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
