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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/contact.css" />
</head>
<body>

	<div class="site-content">
		<%@ include file="../parts/navbarHome.jsp"%>
		<!-- .site-header -->

		<main class="main-content">

		<div class="fullwidth-block">

			<div class="container row">

				<div class="col-md-5">
					<div class="contact-details">
						<img class="map"
							src="${pageContext.request.contextPath}/resources/icons/ptit.jpg">
						<div class="contact-info">
							<address>
								<img
									src="${pageContext.request.contextPath}/resources/icons/icon-marker.png"
									alt="">
								<p>
									Posts and Telecommunications Institute of Technology (South)<br>
									97 Man Thien, Thu Duc city.
								</p>
							</address>
							<img
								src="${pageContext.request.contextPath}/resources/icons/icon-phone.png"
								alt="">+1 800 000 000 <img style="margin-left: 5px;"
								src="${pageContext.request.contextPath}/resources/icons/icon-envelope.png"
								alt="">lacnguyenspringmvc1@gmail.com
						</div>
					</div>
				</div>

				<div class="col-md-5">
					<div class="container contact-form">
						<div class="contact-image">
							<img src="https://image.ibb.co/kUagtU/rocket_contact.png"
								alt="rocket_contact" />
						</div>

						<form:form method="post" modelAttribute="userMess">
							<h3>Drop Us a Message</h3>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<form:input type="text" name="txtName" class="form-control"
											placeholder="Your Name *" value=""
											style="background: #1e202b; color: white;" path="name" />
									</div>
									<div class="form-group">
										<form:input type="text" name="txtEmail" class="form-control"
											placeholder="Your Email *" value=""
											style="background: #1e202b; color: white;" path="userMail" />
									</div>
									<div class="form-group">
										<form:input type="text" name="txtPhone" class="form-control"
											placeholder="Your Phone Number *" value=""
											style="background: #1e202b; color: white;" path="phone" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<form:textarea name="txtMsg" class="form-control"
											placeholder="Your Message *"
											style="width: 100%; height: 125px; background: #1e202b; color: white;"
											path="message" />
									</div>
								</div>
								<div class="col-md-12"
									style="text-align: center; padding-top: 8px;">${message}
								</div>
								<div class="col-md-12"
									style="text-align: center; padding-top: 10px;">
									<%-- <form:errors path="name" />
									<form:errors path="userMail" />
									<form:errors path="phone" />
									<form:errors path="message" />--%>
									<form:errors path="*" element="ul"></form:errors>
								</div>
								<div class="form-group">
									<input type="submit" name="btnSubmit" class="btnContact"
										value="Send Message" />
								</div>
							</div>
						</form:form>
					</div>
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