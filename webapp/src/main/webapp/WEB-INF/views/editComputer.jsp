<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="screen" />
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.css"
	rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="label.navbar" />
			</a><span class="pull-right"> <a class="navbar-brand"
				href="<tags:link computerPage="${computerPage}" search="${search}" pageNumber="1"/>&locale=en">
					<img
					src="${pageContext.request.contextPath}/resources/fonts/uk.png"
					alt="en" style="width: 30px; height: 30px;">
			</a> <a class="navbar-brand"
				href="<tags:link computerPage="${computerPage}" search="${search}"/>&locale=fr">
					<img
					src="${pageContext.request.contextPath}/resources/fonts/fr.png"
					alt="fr" style="width: 30px; height: 30px;">
			</a><a class="navbar-brand" href="javascript:formSubmit()"> <img
					src="${pageContext.request.contextPath}/resources/fonts/logout.png"
					alt="logout" style="width: 30px; height: 30px;"></a>
			</span>

			<form action="<c:url value='j_spring_security_logout' />"
				method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<script>
				function formSubmit() {
					document.getElementById("logoutForm").submit();
				}
			</script>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>
						<spring:message code="label.edit" />
					</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="hidden"
							value="${computer.id}" id="id" name="id" />

						<fieldset>
							<div class="form-group">
								<label for="name"><spring:message
										code="label.computerName" /></label> <input type="text"
									class="form-control" id="name" name="name"
									value="${computer.name}"> ${errors["name"]}
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message
										code="label.introduced" /></label> <input type="date"
									class="form-control" id="introduced" name="introduced"
									value="${computer.introduced}"> ${errors["introduced"]}
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message
										code="label.discontinued" /></label> <input type="date"
									class="form-control" id="discontinued" name="discontinued"
									value="${computer.discontinued}">
								${errors["discontinued"]}
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="label.company" /></label> <select class="form-control"
									id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${companyPage.elementList}" var="company">
										<option value="${company.id}"
											<c:if test="${company.id == computer.company.id}">
										selected</c:if>>${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>

						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>