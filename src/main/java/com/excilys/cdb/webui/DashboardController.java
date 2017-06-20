package com.excilys.cdb.webui;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.dto.ComputerDto;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.webui.utility.Field;
import com.excilys.cdb.webui.utility.mapper.ComputerDtoMapper;

/**
 * Controller for dashboard web page.
 * 
 * @author Elyas Albay
 *
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

	private static final String VIEW = "/WEB-INF/views/dashboard";
	private static final String DASHBOARD = "redirect:/dashboard";
	private static final String ATT_COMPUTER_PG = "computerPage";
	private static final String PAGE_NUMBER = "page_number";
	private static final String PAGE_SIZE = "page_size";
	private static final String ORDER = "order";
	private static final String SEARCH = "search";
	private static final String SELECTION = "selection";

	@Autowired
	private ComputerService computerService;


	@GetMapping
	public ModelAndView get(@RequestParam Map<String, String> params) {
		LOG.info("Get request.");
		
		ModelAndView modelView = new ModelAndView(VIEW);
		Page<Computer> computerPage = new Page<>();
		Page<ComputerDto> computerDtoPage = new Page<>();

		
		// Get parameters from GET request if exists
		String pageNumber = params.get(PAGE_NUMBER);
		String pageSize = params.get(PAGE_SIZE);
		String order = params.get(ORDER);
		String search = params.get(SEARCH);
		
		if (StringUtils.isNotBlank(pageNumber)) {
			int number = Integer.parseInt(pageNumber);

			if (number > 0) {
				computerPage.setPageNumber(number);
			}
		}
		if (StringUtils.isNotBlank(pageSize)) {
			int size = Integer.parseInt(pageSize);

			if (size == 10 || size == 50 || size == 100) {
				computerPage.setPageSize(size);
			}
		}
		if (StringUtils.isNotBlank(order)) {
			if (Field.isValidOrder(order)) {
				computerPage.setOrder(order);
			}
		}

		// Creates a new computerDto page from computer page.
		if (StringUtils.isNotBlank(search)) {
			modelView.addObject(SEARCH, search);
			computerDtoPage = ComputerDtoMapper.createDtoPage(computerService.searchByName(computerPage, search));
		} else {
			computerDtoPage = ComputerDtoMapper.createDtoPage(computerService.getAll(computerPage));
		}
		
		modelView.addObject(ATT_COMPUTER_PG, computerDtoPage);
		
		// Redirects to dashboard page with new parameters
		return modelView;
	}

	@PostMapping
	public String doPost(@RequestParam Map<String, String> params) {
		LOG.info("Post request.");
		
		// Get computer id selection from POST request
		String selection = params.get(SELECTION);
		String pageNumber = params.get(PAGE_NUMBER);
		String pageSize = params.get(PAGE_SIZE);
		String linkParams = "";

		// Get page parameters
		if (StringUtils.isNotBlank(pageNumber)) {
			linkParams += "?" + PAGE_NUMBER + "=" + pageNumber;
		}
		if (StringUtils.isNotBlank(pageSize)) {
			linkParams += "&" + PAGE_SIZE + "=" + pageSize;
		}

		// Delete computers from received identifiers
		for (String id : selection.split(",")) {
			computerService.delete(Long.parseLong(id));
		}

		// Redirects to the GET request.
		return DASHBOARD+linkParams;
	}
}
