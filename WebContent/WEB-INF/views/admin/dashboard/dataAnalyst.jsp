<%@ include file="../../parts/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../parts/meta.jsp"%>
<title>Dashboard</title>
<%@ include file="../../parts/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/dashboard.css" />
</head>
<body>

	<%@ include file="../../parts/navbar.jsp"%>

	<div style="position: relative; top: -550px">
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4"
			style="padding-top:0;">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 ">
			<div>
				<h3 class="h2">Weathers Data</h3>
			</div>
		</div>

		<div>

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Name Code</th>
						<th scope="col">Date</th>
						<th scope="col">Min Temperature</th>
						<th scope="col">Max Temperature</th>
						<th scope="col">Humidity</th>
						<th scope="col">Wind</th>
						<th scope="col">Type of weather</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="s" items="${objects}">
						<tr>
							<td>${s.country.nameCode}</td>
							<td>${s.calendarDate}</td>
							<td>${s.minTemp}</td>
							<td>${s.maxTemp}</td>
							<td>${s.humidity}</td>
							<td>${s.wind}</td>
							<td>${s.type.typeCode}</td>
							<td><a href="/WeatherForecast_Project/dashboard/delete-weather/${s.id}.htm">Delete</a></td>
							<td><a href="/WeatherForecast_Project/dashboard/update-weather/${s.id}.htm">Update</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center" style="margin: auto">

					<c:if test="${page>1}"><li class="page-item"><a class="page-link" href="/WeatherForecast_Project/dashboard/data-weather/${page-1}.htm">Previous</a></li></c:if>
					<li class="page-item"><a class="page-link" href="/WeatherForecast_Project/dashboard/data-weather/${page}.htm">${page }</a></li>
					<c:if test="${objects.size() gt 9}"><li class="page-item"><a class="page-link" href="/WeatherForecast_Project/dashboard/data-weather/${page+1}.htm">Next</a></li></c:if>

				</ul>
			</nav>
		</div>
		
		</main>
	</div>

	<%@ include file="../../parts/footer.jsp"%>
</body>


</html>