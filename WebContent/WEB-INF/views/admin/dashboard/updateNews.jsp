<%@ include file="../../parts/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../parts/meta.jsp"%>
<title>Dashboard</title>
<%@ include file="../../parts/header.jsp"%>
<style type="text/css">
*[id$=errors] {
	color: red;
	font-style: italic;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/dashboard.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
</head>
<body>

	<%@ include file="../../parts/navbar.jsp"%>

	<div style="position: relative; top: -550px">
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4"
			style="padding-top:0;">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom ">
			<div>
				<h3 class="h2">Update News</h3>
			</div>
		</div>

		<div class="form-submit" style="display: flex;">
			<form:form action="{id}.htm" modelAttribute="news">
				<table>
					<tr>

						<td><form:input path="newsId" readonly="true" type="hidden" /></td>
					</tr>
					<tr>
						<td><label>News Date</label></td>
						<td><form:input path="newsDate" placeholder="MM/dd/yyyy"
								style="margin-bottom:20px" /></td>
					</tr>

					<tr>
						<td><label>Title</label></td>
						<td><form:textarea path="title" cols="100"
								style="margin-bottom:20px" /></td>
						<td><form:errors path="title" /></td>
					</tr>
					<tr>
						<td><label>Intro</label></td>
						<td><form:textarea path="intro" cols="100" rows="4"
								style="margin-bottom:20px" /></td>
						<td><form:errors path="intro" /></td>
					</tr>
					<tr>
						<td><label>Body</label></td>
						<td><form:textarea path="body" id="editor"
								style="margin-bottom: 80px;" /></td>
						<td><form:errors path="body" /></td>
					</tr>

				</table>
				<button class="btn btn-dark"
					style="margin-left: 270px; margin-top: 15px;">Update</button>
				<a href="/WeatherForecast_Project/dashboard/data-news/1.htm"
					style="margin-top: 0px;">Back</a>
				<h6 style="margin-left: 270px; margin-top: 30px;">${message}</h6>
				
			</form:form>
		</div>
		</main>
	</div>


	<script>
		CKEDITOR.replace('editor');
	</script>
	<%@ include file="../../parts/footer.jsp"%>
</body>


</html>