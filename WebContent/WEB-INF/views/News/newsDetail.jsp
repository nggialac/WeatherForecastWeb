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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home1.css" />
</head>
<body>

	<div class="site-content">
		<%@ include file="../parts/navbarHome.jsp"%>
		<!-- .site-header -->

		<main class="main-content">
		<div class="fullwidth-block">
			<div class="container">
				<div class="row">
				<c:forEach var="s" items="${objNews}">
					<div class="content col-md-9">
						<div class="post single">
							<h2 class="entry-title">${s.title }</h2>
							<div class="entry-content">
								${s.body }
							</div>
							<h4 style="margin-top: 50px;">Bản tin ngày: ${s.newsDate }</h4>

						</div>

					</div>
					</c:forEach>
					<div class="sidebar col-md-3 col-md-offset-1">
						<div class="widget">
							<h3 class="widget-title">More News</h3>
							<ul class="arrow-list">
								<c:forEach var="s" items="${listNews}" varStatus="loop">
										<c:if test="${loop.index<3}"><li><a href="/WeatherForecast_Project/news/${s[0]}.htm">${s[1]}</a></li>
										</c:if>
									</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		</main>
		<!-- .main-content -->


		<!-- .main-content -->

		<%@ include file="../parts/footerContent.jsp"%>

	</div>

	<%@ include file="../parts/footer.jsp"%>
</body>
</html>