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

	<div style="position: relative; top: -550px;">
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4"
			style="padding-top:0;">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom ">
			<div>
				<h3 class="h2">Upload Image</h3>
			</div>
		</div>

		<div class="form-submit" style="display: flex;">
			
				<table>
					<tr>
						<td><label>Country: ${countryName}</label></td>
						
					</tr>
					<tr>
						<td><label>Type: ${photo_type}</label></td>
						
					</tr>
					<tr>
						<td><label>Size: ${photo_size}</label></td>
					</tr>
					
					<tr>
						<td><img src="${pageContext.servletContext.contextPath}/files/${photo_name}" width="320" height="260"></td>
					</tr>
					
					<tr>
						<td><a href="/WeatherForecast_Project/dashboard/upload-image.htm">BACK</a></td>
					</tr>
					
				</table>
		</div>
		</main>
	</div>



	<%@ include file="../../parts/footer.jsp"%>
</body>


</html>