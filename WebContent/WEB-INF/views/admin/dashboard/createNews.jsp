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
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<body>

	<%@ include file="../../parts/navbar.jsp"%>

	<div style="position: relative; top: -550px">
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4"
			style="padding-top:0;">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom ">
			<div>
				<h3 class="h2">Create News</h3>
			</div>
		</div>

		<div class="form-submit" style="display: flex;">
			<form:form action="createNews.htm" modelAttribute="news" method="POST">
				<table>
					<tr>
						<td><label>Title</label></td>
						<td><form:textarea path="title" cols="100" /></td>
						<td><form:errors path="title" /></td>
					</tr>
					<tr>
						<td><label>Intro</label></td>
						<td><form:textarea path="intro" cols="100" rows="4"/></td>
						<td><form:errors path="intro" /></td>
					</tr>
					<tr>
						<td><label>Body</label></td>
						<td><form:textarea path="body" id="editor"/></td>
						<td><form:errors path="body" /></td>
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


	<script>CKEDITOR.replace('editor');</script>
	<%@ include file="../../parts/footer.jsp"%>
</body>


</html>