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
					<div class="content col-md-9">
						<c:forEach var="s" items="${objNews}">
							<div class="post" style="margin-bottom: 50px;">
								<h2 class="entry-title">${s[1]}</h2>
								<p>${s[2]}</p>
								<a href="/WeatherForecast_Project/news/${s[0]}.htm"
									class="button">Read more</a>
							</div>
							
							
						</c:forEach>

						<div>
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center"
									style="margin: auto">

									<c:if test="${page>1}">
										<li class="page-item"><a class="page-link" style="
    background-color: #262936;
    border: #262936;
"
											href="/WeatherForecast_Project/news/page-${page-1}.htm">Previous</a></li>
									</c:if>
									<li class="page-item"><a class="page-link" style="
    background-color: #262936;
    border: #262936;
"
										href="/WeatherForecast_Project/news/page-${page}.htm">${page }</a></li>
									<c:if test="${objNews.size() gt 4}">
										<li class="page-item"><a class="page-link" style="
    background-color: #262936;
    border: #262936;
"
											href="/WeatherForecast_Project/news/page-${page+1}.htm">Next</a></li>
									</c:if>

								</ul>
							</nav>
						</div>

					</div>
					<div class="sidebar col-md-3 col-md-offset-1">
						<div class="widget">
							<h3 class="widget-title">More News</h3>
							<ul class="arrow-list">
								<c:forEach var="s" items="${objMenu}" varStatus="loop">
									<c:if test="${loop.index<3}">
										<li><a href="/WeatherForecast_Project/news/${s[0]}.htm">${s[1]}</a></li>
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