<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assert the Risk Based on the Loan ID</title>
<link rel="stylesheet" href="/portal/css/bootstrap.min.css">
<link rel="stylesheet" href="/portal/css/name.css/" type="text/css" />
<script src="/portal/js/bootstrap.min.js"></script>
</head>
<body>
<body>
<%@ include file="common/header.jsp"%>
    
	<font color="red">${errorMessage}</font>
	<div class="container">
		<h1><button type="button" class="btn btn-secondary btn-lg btn-block">Risk Assessment</button></h1>
		<%-- <div class="error">${risk}</div> --%>
		<div class="sub-container">
			<div class="error">${status}</div>
			<form:form method="POST" modelAttribute="loan"
				action="/portal/riskassessment">
				<table>
					<tr>
						<td><form:label path="loanId">Loan ID</form:label></td>
						<td><form:input path="loanId" type="text" /></td>
					</tr>
<!-- 
					<td><input type="submit" value="Check" /></td>
					</tr> -->
				</table>
				<div class="btn-width">
					<form:button class="btn btn-primary">Calculate Risk</form:button>
				</div>
			</form:form>
		</div>
	<div class="sub-container">



			<table class="table table-bordered">
				<thead class="thead-light">
					<tr>
						<th scope="col">riskPercent</th>
						<th scope="col">assessedDate</th>

					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">${risk.getRiskPercent()}</th>
						<td>${risk.getAssessedDate()}</td>

					</tr>
				</tbody>
			</table>

		</div>


	</div>
	<%@ include file="common/footer.jsp"%>
</body>
</html>