<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/portal/css/bootstrap.min.css">
<link rel="stylesheet" href="/portal/css/name.css/" type="text/css" />
<script src="/portal/js/bootstrap.min.js"></script>

<style>
.image{
margin-left: 60%;
margin-right: 15px;
}
.text{
margin-left: 73%;
margin-right: 1px;
}
</style>
</head>
<body>
<%@ include file="common/header.jsp"%>
<div class="container">

		<h1><button type="button" class="btn btn-secondary btn-lg btn-block">Types Of Collateral</button></h1>
		<div class="sub-container">
		<div class="row">
			<div class="col-lg-4">
			<a href="/portal/cashdeposit">
				<img class="image" src="/portal/image/img4.jpg" alt="Snow"
					style="width: 350px; height: 350px;">
					<p class="text">Cash Deposit</p></a>
			</div>
			<div class="col-lg-4">
			<a href="/portal/realestate">
				<img class="image" src="/portal/image/img5.jpg" alt="Mountains"
					style="width: 350px; height: 350px;">
					<p class="text">Real Estate</p></a>
			</div>
		
		
		
		</div>
		</div>
		</div>

	
	<%@ include file="common/footer.jsp"%>
	
</body>
</html>