<%@ include file="../parts/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../parts/meta.jsp"%>
<title>Home Page</title>
<%@ include file="../parts/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home1.css" />
</head>
<body>

	<div class="site-content">

		<%@ include file="../parts/navbarHome.jsp"%>
		<!-- .site-header -->
		<div class="forecast-table" style="margin-bottom: 40px;">

			<div class="container">

				<div class="forecast-container">
					<div class="today forecast">
						<div class="forecast-header">
							<div class="day">Weather</div>
							<div class="date">${object1}</div>
						</div>
						<!-- .forecast-header -->
						<div class="forecast-content">
							<div class="location">${object0}</div>
							<div class="degree"
								style="text-align: center; justify-content: center;">
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
								alt="">${object5} km/h</span> <span>
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
			</div>
		</div>

		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-sm-8">
					<form action="nation/country.htm" class="subscribe-form">
						<input name="cn" type="text" placeholder="Enter your nation..."
							style="padding: 20px 100px 20px 20px; background: #323544; width: 100%; color: white;" />
						<input type="submit"
							style="color: white; background: #009ad8; padding: 10px 20px; position: absolute; right: 20px; top: 5px; bottom: 5px;">
					</form>

				</div>
			</div>
		</div>

		<div class="fullwidth-block" style="padding-bottom: 10px;">
			<div class="container">
				<h2 class="section-title">Countries</h2>
				<div class="row">
					<c:forEach var="f" items="${flag}" varStatus="i">
						<div class="col-md-2 col-sm-6">
							<a href="/WeatherForecast_Project/nation/country.htm?cn=${f[1]}">
								<figure class="live-camera">
									<img
										src="${pageContext.request.contextPath}/resources/flags/${f[0]}.png"
										alt="" width="90" height="70">
								</figure>
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>


		<main class="main-content">
		<div class="fullwidth-block">
			<div class="container">
				<h2 class="section-title">Beauty of World</h2>
				<div class="row">
					<c:forEach var="b" items="${bi}" varStatus="i">
						<div class="col-md-3 col-sm-6">
							<div class="live-camera">
								<small class="date">${b[2]}</small>
								<figure class="live-camera-cover">
									<img src="${pageContext.request.contextPath}/files/${b[1]}"
										alt="" width="250" height="190">
								</figure>
								<h3 class="location" margin-bottom="10px">${b[0]}</h3>

							</div>
						</div>
					</c:forEach>
				</div>
				<div class="row" style="margin-top: 25px;">
					<c:forEach var="b" items="${bi2}" varStatus="i">
						<div class="col-md-3 col-sm-6">
							<div class="live-camera">
								<small class="date">${b[2]}</small>
								<figure class="live-camera-cover">
									<img src="${pageContext.request.contextPath}/files/${b[1]}"
										alt="" width="250" height="200">
								</figure>
								<h3 class="location" margin-bottom="10px">${b[0]}</h3>
								<%-- <small class="date">${b[2]}</small>--%>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

		</main>
		<!-- .main-content -->

		<%@ include file="../parts/footerContent.jsp"%>

	</div>

	<%@ include file="../parts/footer.jsp"%>
</body>
</html>