<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href=${pageContext.request.contextPath}/css/bootstrap.min.css
	rel="stylesheet" type="text/css" media="screen" />
<link href=${pageContext.request.contextPath}/css/font-awesome.css
	rel="stylesheet" type="text/css" media="screen" />
<link href=${pageContext.request.contextPath}/css/main.css
	rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value="${computer.id}" id="id" name="id" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" value="${computer.name}">
							</div> ${errors["computerName"]}
							
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" value="${computer.introduced}">
							</div> ${errors["introduced"]}
							
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" value="${computer.discontinued}">
							</div> ${errors["discontinued"]}
							
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${companyPage.elementList}" var="company">
										<option value="${company.id}" <c:if test="${company.id == computer.company.id}">
										selected
										</c:if> >${company.name}</option>
									</c:forEach>
								</select>
							</div> ${errors["companyId"]}
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