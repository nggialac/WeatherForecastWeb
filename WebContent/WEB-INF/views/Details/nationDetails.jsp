<%@ include file="../parts/tag.jsp"%>
<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../parts/meta.jsp"%>
<title>Home Page</title>
<%@ include file="../parts/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home.css" />
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/nationDetail.css" />
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home1.css" />
</head>
<body>

	<div class="site-content">
		<%@ include file="../parts/navbarHome.jsp"%>
		<!-- .site-header -->

		<div class="overview">
			<div class="container px-sm-2 py-5">
				<div class="row d-flex justify-content-center">
					<c:forEach var="b" items="${test0}">
						<div class="card text-center pt-4 border-0">
							<h4 class="mb-0">${b[1]}</h4>
							<small class="text-muted mb-3">${b[0]}</small>
							<h2 class="large-font">${Math.round((b[2] + b[3])/2)}&#176;</h2>
							<div class="text-center mt-3 mb-4">
								<img class="city-symbol"
									src="${pageContext.request.contextPath}/resources/Countries/${b[0]}.svg">
							</div>
					</c:forEach>

					<div class="row d-flex px-3 mt-auto">
						<c:forEach var="c" items="${test }">
							<div class="d-flex flex-column block">
								<small class="text-muted mb-0">${c[1]}</small>
								<div class="text-center">
									<img class="symbol-img"
										src="${pageContext.request.contextPath}/resources/icons/${c[5]}.svg">
								</div>
								<h6>
									<strong>${Math.round((c[2] + c[3])/2)}</strong>
								</h6>
							</div>
						</c:forEach>

					</div>

				</div>
				<c:forEach var="b" items="${test0}">
					<div class="card pt-6">
						<h2 style="margin-top: 20px">${b[0]}</h2>
						<p>${b[4]}</p>
					</div>
				</c:forEach>
			</div>

			<div class="row d-flex" style="margin-top: 20px;">
				<c:forEach var="b" items="${bi}" varStatus="i">
					<div class="col-md-3 col-sm-6">

						<div class="live-camera">

							<figure class="live-camera-cover">
								<img src="${pageContext.request.contextPath}/files/${b[1]}"
									alt="" width="265" height="190">
							</figure>


						</div>
					</div>
				</c:forEach>

			</div>
		</div>


		
	<!-- .main-content -->

	<%@ include file="../parts/footerContent.jsp"%>

	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/nationDetail.js"></script>

	<%@ include file="../parts/footer.jsp"%>
</body>
</html>