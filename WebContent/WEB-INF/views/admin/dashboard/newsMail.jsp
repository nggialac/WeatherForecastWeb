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
				<h3 class="h2">Mailer</h3>
			</div>
		</div>

		<div class="form-submit" style="display:flex; "> 
			<form:form action="sent-mail.htm" method="post">
			<table>
					<tr>
						<td><label>From</label></td>
						<td><input name="from" value="lacnguyenspringmvc2@gmail.com" style="width: 100%" readonly="true"></td>
					</tr>
					<tr>
						<td><label>To</label></td>
						<td><input name="to" style="width: 100%"></td>
					</tr>
					<tr>
						<td><label>Subject</label></td>
						<td><input name="subject" style="width: 100%"></td>
					</tr>
					<tr>
						<td><label>Body</label></td>
						<td><textarea name="body" rows="5" cols="40"></textarea></td>
					</tr>
					
				
					
				<tr>
					<td><button class="btn btn-dark" style="margin-left: 80px; margin-top: 20px;">Send</button></td>
				</tr>
				</table>
			</form:form>
			
			<div>
				${message }
			</div>
		</div>
		</main>
	</div>



	<%@ include file="../../parts/footer.jsp"%>
</body>


</html>