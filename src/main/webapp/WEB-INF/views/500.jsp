<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" 
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet" type="text/css" 
	media="screen">
<link href="resources/css/main.css" rel="stylesheet" type="text/css" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message code="label.navbar"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				<spring:message code="label.error500"/> <br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/dashboard.js"></script>

</body>
</html>