<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href=${pageContext.request.contextPath}/css/main.css
	rel="stylesheet" type="text/css" media="screen" />
<link href=${pageContext.request.contextPath}/css/bootstrap.min.css
	rel="stylesheet" type="text/css" media="screen" />
<link href=${pageContext.request.contextPath}/css/font-awesome.css
	rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${computerPage.numberOfPages} Computers found
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer.html">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computerPage.elementList}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="editComputer.html" onclick="">${computer.name}</a></td>
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
			<c:choose>
				<c:when test="${computerPage.pageNumber < 4}">
					<c:forEach var="i" begin="1" end="5">
						<li>
							<a href=${pageContext.request.contextPath}/dashboard?page_number=${i}>${i}</a>
						</li>
					</c:forEach>
				</c:when>
				<c:when test="${computerPage.pageNumber > (computerPage.numberOfPages/computerPage.pageSize)-3}">
					<c:forEach var="i" begin="1" end="5">
						<li>
							<a href=${pageContext.request.contextPath}/dashboard?page_number=${(computerPage.numberOfPages/computerPage.pageSize)-5+i}>${(computerPage.numberOfPages/computerPage.pageSize)-5+i}</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="1" end="5">
						<li>
							<a href=${pageContext.request.contextPath}/dashboard?page_number=${computerPage.pageNumber-3+i}>${computerPage.pageNumber -3 + i}</a>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
				<!-- <li><a href="#" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a></li>

				<li><a href="#">1</a></li>
				<li><a
					href=${pageContext.request.contextPath}/dashboard?page_number=2>2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>-->
			</ul>


			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default">10</button>
				<button type="button" class="btn btn-default">50</button>
				<button type="button" class="btn btn-default">100</button>
			</div>
	</footer>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/dashboard.js"></script>

</body>
</html>