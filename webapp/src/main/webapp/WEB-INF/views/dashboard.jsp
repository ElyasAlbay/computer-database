<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet"
	type="text/css" media="screen" />
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="label.navbar" />
			</a> <a class="navbar-brand pull-right"
				href="<tags:link computerPage="${computerPage}" search="${search}" pageNumber="1"/>&locale=en">
				en </a> <a class="navbar-brand pull-right"
				href="<tags:link computerPage="${computerPage}" search="${search}"/>&locale=fr">
				fr </a>
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
						action=<tags:link computerPage="${computerPage}" search="${search}" pageNumber="1"/>
						method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message code="label.searchField"/>"
							value="${search}" /> <input type="submit" id="searchsubmit"
							value="<spring:message code="label.filterButton"/>"
							class="btn btn-primary" />
					</form>
				</div>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="pull-right">
						<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
								code="label.addButton" /></a> <a class="btn btn-default"
							id="editComputer" href="#"
							onclick="$.fn.toggleEditMode('<spring:message code="label.editButton"/>', '<spring:message code="label.viewButton"/>');">
							<spring:message code="label.editButton" />
						</a>
					</div>
				</sec:authorize>
			</div>
		</div>

		<form id="deleteForm"
			action=<tags:link computerPage="${computerPage}" search="${search}"/>
			method="POST">
			<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<!-- Variable declarations for passing labels as parameters -->
							<th class="editMode" style="width: 60px; height: 22px;"><input
								type="checkbox" id="selectall" /> <span
								style="vertical-align: top;"> - <a href="#"
									id="deleteSelected"
									onclick="$.fn.deleteSelected('<spring:message code="label.deleteConfirm"/>');">
										<i class="fa fa-trash-o fa-lg"></i>
								</a>
							</span></th>
						</sec:authorize>
						<!-- Table header for Computer Name -->
						<th><a
							href=<tags:link computerPage="${computerPage}" search="${search}" pageOrder="name"/>>
								<spring:message code="label.computerName" />
						</a></th>
						<!-- Table header for Introduced Date -->
						<th><a
							href=<tags:link computerPage="${computerPage}" search="${search}" pageOrder="introduced"/>>
								<spring:message code="label.introduced" />
						</a></th>
						<!-- Table header for Discontinued Date -->
						<th><a
							href=<tags:link computerPage="${computerPage}" search="${search}" pageOrder="discontinued"/>>
								<spring:message code="label.discontinued" />
						</a></th>
						<!-- Table header for Company -->
						<th><a
							href=<tags:link computerPage="${computerPage}" search="${search}" pageOrder="company.name"/>>
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
							<td><a
								<sec:authorize access="hasRole('ROLE_ADMIN')">href=editComputer?computer_id=${computer.id}</sec:authorize>
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
			<tags:pagination computerPage="${computerPage}" search="${search}" />

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a
					href=<tags:link computerPage="${computerPage}" search="${search}" pageSize="10" pageNumber="1"/>>
					<button type="button" class="btn btn-default">10</button>
				</a> <a
					href=<tags:link computerPage="${computerPage}" search="${search}" pageSize="50" pageNumber="1"/>>
					<button type="button" class="btn btn-default">50</button>
				</a> <a
					href=<tags:link computerPage="${computerPage}" search="${search}" pageSize="100" pageNumber="1"/>>
					<button type="button" class="btn btn-default">100</button>
				</a>
			</div>
		</div>
	</footer>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>

</body>
</html>