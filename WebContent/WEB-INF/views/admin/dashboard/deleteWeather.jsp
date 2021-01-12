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
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom ">
			<div>
				<h3 class="h2">Delete Weather</h3>
			</div>
		</div>

		<div class="form-submit" style="display: flex;">
			<form:form action="{id}.htm" modelAttribute="weatherDaily">
				<table>
					<tr>
						
						<td><form:input path="id" readonly="true" type="hidden"/></td>
					</tr>
					<tr>
						<td><label>Country</label></td>
						<td><form:input path="country.nameCode" disabled="true" /></td>
					</tr>
					<tr>
						<td><label>Date</label></td>
						<td><form:input path="calendarDate" disabled="true"/></td>
					</tr>
					<tr>
						<td><label>Max Temperature</label></td>
						<td><form:input path="maxTemp" disabled="true"/></td>
					</tr>
					<tr>
						<td><label>Min Temperature</label></td>
						<td><form:input path="minTemp" disabled="true" /></td>
					</tr>
					<tr>
						<td><label>Humidity</label></td>
						<td><form:input path="humidity" disabled="true"/></td>
					</tr>
					<tr>
						<td><label>Wind</label></td>
						<td><form:input path="wind" disabled="true"/></td>
					</tr>
					
					<tr>
						<td><label>Type of Weather</label></td>
						<td><form:input path="type.typeCode" disabled="true" /></td>
					</tr>

					<tr>
						<td><button class="btn btn-dark"
								style="margin-left: 80px; margin-top: 20px;">Delete</button></td>
						<td><a
							href="/WeatherForecast_Project/dashboard/data-weather/1.htm"
							style="margin-left: 10px; padding-top: 10px;">Back</a></td>
					</tr>
				</table>
			</form:form>

			<div style="margin-left: 20px;">${message }</div>
		</div>
		</main>
	</div>



	<%@ include file="../../parts/footer.jsp"%>
</body>


</html>