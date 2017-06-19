<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="resources/css/main.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" media="screen" />
<link href="resources/css/font-awesome.css" rel="stylesheet"
	type="text/css" media="screen" />
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> 
			<spring:message	code="label.navbar" />
			</a>
			<a class="navbar-brand pull-right" href=${pageContext.request.contextPath}/dashboard?page_number=1&page_size=${computerPage.pageSize}&search=${search}&order=${computerPage.order}&locale=en>
			en
			</a>
			<a class="navbar-brand pull-right" href=${pageContext.request.contextPath}/dashboard?page_number=1&page_size=${computerPage.pageSize}&search=${search}&order=${computerPage.order}&locale=fr>
			fr
			</a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${computerPage.numberOfElements}
				<spring:message code="label.pageTitle" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm"
						action=${pageContext.request.contextPath}/dashboard?page_size=${computerPage.pageSize}&search=${search}
						method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message code="label.searchField"/>"
							value="${search}" /> <input type="submit" id="searchsubmit"
							value="<spring:message code="label.filterButton"/>"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
							code="label.addButton" /></a> <a class="btn btn-default"
						id="editComputer" href="#"
						onclick="$.fn.toggleEditMode('<spring:message code="label.editButton"/>', '<spring:message code="label.viewButton"/>');">
						<spring:message	code="label.editButton" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm"
			action=${pageContext.request.contextPath}/dashboard?page_number=${computerPage.pageNumber}&page_size=${computerPage.pageSize}
			method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<!-- Table header for Computer Name -->
						<th><a id="orderByName"
							href="${pageContext.request.contextPath}/dashboard?page_number=${computerPage.pageNumber}&page_size=${computerPage.pageSize}&search=${search}&order=name">
								<spring:message code="label.computerName" />
						</a></th>
						<!-- Table header for Introduced Date -->
						<th><a id="orderByName"
							href="${pageContext.request.contextPath}/dashboard?page_number=${computerPage.pageNumber}&page_size=${computerPage.pageSize}&search=${search}&order=introduced">
								<spring:message code="label.introduced" />
						</a></th>
						<!-- Table header for Discontinued Date -->
						<th><a id="orderByName"
							href="${pageContext.request.contextPath}/dashboard?page_number=${computerPage.pageNumber}&page_size=${computerPage.pageSize}&search=${search}&order=discontinued">
								<spring:message code="label.discontinued" />
						</a></th>
						<!-- Table header for Company -->
						<th><a id="orderByName"
							href="${pageContext.request.contextPath}/dashboard?page_number=${computerPage.pageNumber}&page_size=${computerPage.pageSize}&search=${search}&order=company.name">
								<spring:message code="label.company" />
						</a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computerPage.elementList}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href=editComputer?computer_id=${computer.id}
								onclick="">${computer.name}</a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.company.name}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${computerPage.pageNumber > 1}">
					<li><a
						href=${pageContext.request.contextPath}/dashboard?page_number=1&page_size=${computerPage.pageSize}&search=${search}&order=${computerPage.order}
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>

				<c:choose>
					<c:when test="${computerPage.pageNumber < 4}">
						<c:forEach var="i" begin="1" end="5">
							<li><a
								href=${pageContext.request.contextPath}/dashboard?page_number=${i}&page_size=${computerPage.pageSize}&search=${search}&order=${computerPage.order}>${i}</a>
							</li>
						</c:forEach>
					</c:when>
					<c:when
						test="${computerPage.pageNumber > computerPage.numberOfPages-3}">
						<c:forEach var="i" begin="1" end="5">
							<li><a
								href=${pageContext.request.contextPath}/dashboard?page_number=${computerPage.numberOfPages-5+i}&page_size=${computerPage.pageSize}&search=${search}&order=${computerPage.order}>${computerPage.numberOfPages-5+i}</a>
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="i" begin="1" end="5">
							<li><a
								href=${pageContext.request.contextPath}/dashboard?page_number=${computerPage.pageNumber-3+i}&page_size=${computerPage.pageSize}&search=${search}&order=${computerPage.order}>${computerPage.pageNumber-3+i}</a>
							</li>
						</c:forEach>
					</c:otherwise>
				</c:choose>

				<c:if test="${computerPage.pageNumber < computerPage.numberOfPages}">
					<li><a
						href=${pageContext.request.contextPath}/dashboard?page_number=${computerPage.numberOfPages}&page_size=${computerPage.pageSize}&search=${search}&order=${computerPage.order}
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>


			<div class="btn-group btn-group-sm pull-right" role="group">
				<a
					href=${pageContext.request.contextPath}/dashboard?page_size=10&search=${search}&order=${computerPage.order}>
					<button type="button" class="btn btn-default">10</button>
				</a> <a
					href=${pageContext.request.contextPath}/dashboard?page_size=50&search=${search}&order=${computerPage.order}>
					<button type="button" class="btn btn-default">50</button>
				</a> <a
					href=${pageContext.request.contextPath}/dashboard?page_size=100&search=${search}&order=${computerPage.order}>
					<button type="button" class="btn btn-default">100</button>
				</a>
			</div>
	</footer>
	<script src=resources/js/jquery.min.js></script>
	<script src=resources/js/bootstrap.min.js></script>
	<script src=resources/js/dashboard.js></script>

</body>
</html>