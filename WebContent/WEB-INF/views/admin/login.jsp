<%@ include file="../parts/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../parts/meta.jsp"%>
<title>Home Page</title>
<%@ include file="../parts/header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css" />
</head>
<body>

	<div class="container login-container">
            <div class="row">
                <div class="col-md-6 login-form-2">
                    <h3>ADMIN LOGIN</h3>
                    <form id="admin" action="admin.htm" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Your Email *" value="" name="email" />
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Your Password *" value="" name="pass" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" value="Login" />
                        </div>
                    </form>
                    <h4 class="error">${error }</h4>
                </div>
            </div>
        </div>
	
	<%@ include file="../parts/footer.jsp"%>
</body>
</html>