<%@ taglib tagdir="/WEB-INF/resources/tags" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="java.lang.StringBuilder"%>

<%@ attribute name="computerPage" required="true" rtexprvalue="true"
	type="com.excilys.cdb.model.Page"%>
<%@ attribute name="search" required="false" rtexprvalue="true"%>

<%
	int number = computerPage.getPageNumber();
	int size = computerPage.getPageSize();
	int last = computerPage.getNumberOfPages();

	StringBuilder sb = new StringBuilder("dashboard?");

	sb.append("page_size=").append(size).append("&amp;");
	sb.append("order=").append(computerPage.getOrder()).append("&amp;");
	if (search != null && !search.trim().isEmpty()) {
		sb.append("search=").append(search).append("&amp;");
	}
	sb.append("page_number=");

	String before = sb.toString();
%>

<ul class="pagination">

	<c:if test="${omputerPage.pageNumber >1}">
		<li><a href="<%= before %>1" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>>&lsaquo;&lsaquo;
		</a></li>
	</c:if>

	<c:choose>
		<c:when test="${computerPage.pageNumber < 4}">
			<c:forEach var="i" begin="1" end="5">
				<li><a href="<%= before %>${i}">${i}</a></li>
			</c:forEach>

		</c:when>
		
		<c:when test="${computerPage.pageNumber > computerPage.numberOfPages-3}">
			<c:forEach var="i" begin="1" end="5">
				<li><a href="<%= before %>${computerPage.numberOfPages-5+i}">${computerPage.numberOfPages-5+i}</a>
				</li>
			</c:forEach>
		</c:when>

		<c:otherwise>
			<c:forEach var="i" begin="1" end="5">
				<li><a href="<%= before %>${computerPage.pageNumber-3+i}">${computerPage.pageNumber-3+i}</a>
				</li>
			</c:forEach>
		</c:otherwise>
	</c:choose>

	<c:if test="${computerPage.pageNumber < computerPage.numberOfPages}">
		<li><a href="<%= before %>${computerPage.numberOfPages}">&rsaquo;
		</a></li>
	</c:if>

</ul>