package com.excilys.cdb.webui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.mapper.CompanyDtoMapper;
import com.excilys.cdb.mapper.ComputerDtoMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ComputerServiceImpl;
import com.excilys.cdb.ui.Page;

/**
 * Servlet for dashboard web page.
 * 
 * @author Elyas Albay
 *
 */
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 6646407915881068151L;
	private static final String view = "/WEB-INF/views/dashboard.jsp";

	private ComputerService computerService = ComputerServiceImpl.INSTANCE;
	private ComputerDtoMapper computerDtoMapper;
	private CompanyDtoMapper companyDtoMapper;

	
	/**
	 * Class constructor.
	 */
	public DashboardController() {
		companyDtoMapper = new CompanyDtoMapper();
		computerDtoMapper = new ComputerDtoMapper(companyDtoMapper);
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page<Computer> computerPage = new Page<>();
		Page<ComputerDto> computerDtoPage = new Page<>();

		if (request.getParameter("page_number") != null) {
			computerPage.setPageNumber(Integer.parseInt(request.getParameter("page_number")));
		}
		if (request.getParameter("page_size") != null) {
			computerPage.setPageSize(Integer.parseInt(request.getParameter("page_size")));
		}

		computerDtoPage = computerDtoMapper.createDtoPage(computerService.listRequest(computerPage));
		
		request.setAttribute("computerPage", computerDtoPage);
		
		
		this.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

}
