<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<link rel="stylesheet"  href="/portal/css/bootstrap.min.css">
<link rel="stylesheet" href="/portal/css/name.css/" type="text/css"/>
<script src="/portal/js/bootstrap.min.js"></script>
<style type="text/css">
body{
background-image: url("/portal/image/bank_background.jpg");
}

.login-container{
	height: 500px;
}
</style>
</head>
<body>
	<%@ include file="common/header1.jsp"%>

	<div class="container">

				<div class="login-container">
					<%-- <spring:url value="/home" var="homePageLink"></spring:url>
                ${homePageLink} --%>
					
						<h2 style="color:red"><center> ${invalid_cred} </center></h2>
						<h2><center>Login Form</center></h2>
						<spring:url value="/login" var="loginUrl"></spring:url>
						<form:form method="post" modelAttribute="model"
							action="${loginUrl}">
							<spring:bind path="userName">
								<div class="form-group ">
									<label for="userName">Username</label>
									<form:input path="userName" type="text"
										class="form-control ${status.error ? 'is-invalid' : ''}"
										id="userName" placeholder="Username" />
									<form:errors path="userName" class="invalid-feedback" />
								</div>
							</spring:bind>
							<spring:bind path="password">
								<div class="form-group ">
									<label for="password">Password</label>
									<form:input path="password" type="password"
										class="form-control ${status.error ? 'is-invalid' : ''}"
										id="password" placeholder="Password" />
									<form:errors path="password" class="invalid-feedback" />
								</div>
							</spring:bind>
							
							
							
							
							<spring:bind path="authority">
								<div class="form-group ">
									<label for="authority">Authority : </label><br>
									<select name="authority" id="authority">
										<option value="customer">Customer</option>
										<option value="admin">Admin</option>
									</select>
								</div>
							</spring:bind>
							
							
							
							
							<br></br>
							<center>
							<button type="submit" class="btn btn-light">Login</button></center>
						</form:form>
						<div class="error">${status}</div>
					</div>
				</div>
		
			
			<%@ include file="common/footer.jsp"%>
</body>
</html>