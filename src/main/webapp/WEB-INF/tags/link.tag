<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="java.lang.StringBuilder"%>

<%@ attribute name="computerPage" required="true" rtexprvalue="true"
	type="com.excilys.cdb.model.Page"%>
<%@ attribute name="pageNumber" required="false" rtexprvalue="true"%>
<%@ attribute name="pageSize" required="false" rtexprvalue="true"%>
<%@ attribute name="pageOrder" required="false" rtexprvalue="true"%>
<%@ attribute name="search" required="false" rtexprvalue="true"%>

<%
	int number = computerPage.getPageNumber();
	int size = computerPage.getPageSize();
	String order = computerPage.getOrder();

	StringBuilder sb = new StringBuilder("dashboard?");

	if (search != null && !search.trim().isEmpty()) {
		sb.append("search=").append(search).append("&amp;");
	}
	if (pageOrder != null && !pageOrder.trim().isEmpty()) {
		order = pageOrder;
	}
	if (pageSize != null && !pageSize.trim().isEmpty()) {
		size = Integer.parseInt(pageSize);
	}
	if (pageNumber != null && !pageNumber.trim().isEmpty()) {
		number = Integer.parseInt(pageNumber);
	}
	
	sb.append("order=").append(order).append("&amp;");
	sb.append("page_size=").append(size).append("&amp;");
	sb.append("page_number=").append(number);

	String link = sb.toString();
%>

<%=link%>