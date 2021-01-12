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
<style type="text/css">
*[id$=errors] {
	color: red;
	font-style: italic;
}
</style>
<body>

	<%@ include file="../../parts/navbar.jsp"%>

	<div style="position: relative; top: -550px">
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4"
			style="padding-top:0;">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom ">
			<div>
				<h3 class="h2">Insert Weather</h3>
			</div>
		</div>

		<div class="form-submit" style="display: flex;">
			<form:form action="insert-weather.htm" modelAttribute="weatherDaily">
				<table>
					<tr>
						<td><label>Country</label></td>
						<td><form:select path="country.nameCode" items="${countries}"
								itemLabel="countryName" itemValue="nameCode">

							</form:select></td>
					</tr>
					<tr>
						<td><label>Date</label></td>
						<td><form:input path="calendarDate"
								placeholder="MM/dd/yyyy" /></td>
						<td><form:errors path="calendarDate" /></td>
					</tr>
					<tr>
						<td><label>Max Temperature</label></td>
						<td><form:input path="maxTemp" placeholder="oC"/></td>
						<td><form:errors path="maxTemp" /></td>
					</tr>
					<tr>
						<td><label>Min Temperature</label></td>
						<td><form:input path="minTemp" placeholder="oC"/></td>
						<td><form:errors path="minTemp" /></td>
					</tr>
					<tr>
						<td><label>Humidity</label></td>
						<td><form:input path="humidity" placeholder="%"/></td>
						<td><form:errors path="humidity" /></td>
					</tr>
					<tr>
						<td><label>Wind</label></td>
						<td><form:input path="wind" placeholder="km/h"/></td>
						<td><form:errors path="wind" /></td>
					</tr>
					<tr>
						<td><label>Type Weather</label></td>
						<td><form:select path="type.typeCode" items="${types}"
								itemLabel="typeWeather" itemValue="typeCode">
							</form:select></td>
					</tr>
					

					<tr>
						<td><button class="btn btn-dark"
								style="margin-left: 80px; margin-top: 20px;">INSERT</button></td>
						<td style="padding-top: 20px; padding-left: 30px">${message}</td>
					</tr>
				</table>
			</form:form>

		</div>
		</main>
	</div>



	<%@ include file="../../parts/footer.jsp"%>
</body>


</html>