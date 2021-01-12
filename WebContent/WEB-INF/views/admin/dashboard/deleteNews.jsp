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
				<h3 class="h2">Delete News</h3>
			</div>
		</div>

		<div class="form-submit" style="display: flex;">
			<form:form action="{id}.htm" modelAttribute="news">
				<table>
					<tr>
						<td><form:input path="newsId" readonly="true" type="hidden" /></td>
					</tr>
					<tr>
						<td><label>Title</label></td>
						<td><form:textarea path="title" cols="100" /></td>
					</tr>
					<tr>
						<td><label>Intro</label></td>
						<td><form:textarea path="intro" cols="100" rows="4" /></td>
					</tr>
					<tr>
						<td><label>Body</label></td>
						<td><form:textarea path="body" id="editor" /></td>
					</tr>
					<tr>
						<td><button class="btn btn-dark"
								style="margin-left: 80px; margin-top: 20px;">DELETE</button></td>
								<td style="padding-bottom: 0;"><a
							href="/WeatherForecast_Project/dashboard/data-news/1.htm"
							style="">Back</a></td>
				
					</tr>
				</table>
					<h6 style="margin-left: 60px; margin-top: 20px;">${message}</h6>
			</form:form>

		</div>
	
		</main>
	</div>

</body>


</html>