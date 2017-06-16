package com.excilys.cdb.webui;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for error pages.
 * 
 * @author Elyas Albay
 *
 */
@Controller
@RequestMapping("/errors")
public class ErrorController {
	private static final String ERROR403 = "/WEB-INF/views/403";
	private static final String ERROR404 = "/WEB-INF/views/404";
	private static final String ERROR500 = "/WEB-INF/views/500";

	@GetMapping
	public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

		ModelAndView modelView = new ModelAndView();
		int httpErrorCode = getErrorCode(httpRequest);

		switch (httpErrorCode) {
		case 403: {
			modelView.setViewName(ERROR403);
			break;
		}
		case 404: {
			modelView.setViewName(ERROR404);
			break;
		}
		case 500: {
			modelView.setViewName(ERROR500);
			break;
		}
		default:
			modelView.setViewName("redirect:/dashboard");
			break;
		}

		return modelView;
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}