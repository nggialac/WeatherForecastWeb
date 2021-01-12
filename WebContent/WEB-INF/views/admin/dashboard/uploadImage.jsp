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

	<div class="tieu-de" style="position: relative; top: -550px;">
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4"
			style="padding-top:0;">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom ">
			<div>
				<h3 class="h2">Upload Image</h3>
			</div>
		</div>

		<div class="form-submit" style="">
			<form:form action="upload-success.htm" enctype="multipart/form-data"
				method="POST" modelAttribute="beautyImage">
				<table>
					<tr>
						<td><label>Country</label></td>
						<td><form:select path="country.nameCode" items="${countries}"
								itemLabel="countryName" itemValue="nameCode">

							</form:select></td>
					</tr>
					<tr>
						<td><label>Image</label></td>
						<td><input type="file" name="photo"></td>
					</tr>
					<tr>
						<td><button class="btn btn-dark"
								style="margin-left: 80px; margin-top: 20px;">Update</button></td>
					</tr>
				</table>
			</form:form>

			<div>${message }</div>
		</div>
		</main>
	</div>



	<%@ include file="../../parts/footer.jsp"%>
</body>


</html>