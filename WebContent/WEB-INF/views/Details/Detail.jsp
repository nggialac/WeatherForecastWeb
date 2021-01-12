<%@ include file="../parts/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../parts/meta.jsp"%>
<title>Home Page</title>
<%@ include file="../parts/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home.css" />
</head>
<body>

	<div class="site-content">
		<%@ include file="../parts/navbarHome.jsp"%>
		<!-- .site-header -->

		<div class="forecast-table" style="margin-bottom: 0px;">
			<div class="container">

				<div class="forecast-container">


					<div class="today forecast">
						<div class="forecast-header">
							<div class="day"></div>
							<div class="date">${object1}</div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="location">${object0}</div>
							<div class="degree">
								<div class="num">${Math.round((object3 + object2)/2)}<sup>o</sup>C
								</div>
								<div class="forecast-icon">
									<img
										src="${pageContext.request.contextPath}/resources/icons/${object6}.svg"
										alt="" width=90>
								</div>
							</div>
							<span><img
								src="${pageContext.request.contextPath}/resources/icons/icon-umberella.png"
								alt="">${object4}</span> <span><img
								src="${pageContext.request.contextPath}/resources/icons/icon-wind.png"
								alt="">${object5} km/h</span> <span><img
								src="${pageContext.request.contextPath}/resources/icons/icon-compass.png"
								alt="">East</span>
						</div>
					</div>

					<c:forEach var="s" items="${objects}" varStatus="loop">

						<div class="forecast">
							<div class="forecast-header">
								<div class="day">${s[1]}</div>
							</div>
							<!-- .forecast-header -->
							<div class="forecast-content">
								<div class="forecast-icon">
									<img
										src="${pageContext.request.contextPath}/resources/icons/${s[6]}.svg"
										alt="" width=48>
								</div>
								<div class="degree">
									${s[3] }<sup>o</sup>C
								</div>
								<small>${s[2] }<sup>o</sup></small>
							</div>
						</div>

					</c:forEach>

				</div>
				<a href="WeatherForecast_Project/BR.htm"><h3>alo</h3></a>
			</div>
		</div>


	</div>

	<%@ include file="../parts/footer.jsp"%>
</body>
</html>