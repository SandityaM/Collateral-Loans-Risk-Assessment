<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cash Deposit Details</title>
<link rel="stylesheet" href="/portal/css/name.css/" type="text/css" />
<script src="/portal/js/bootstrap.min.js"></script>
</head>
<body>
	<h2>Submitted Real Estate Details</h2>
	<table>
		<tr>
			<td>Collateral Id:</td>
			<td>${​​​​​​​collateralId}​​​​​​​</td>
		</tr>
		<tr>
			<td>LoanId:</td>
			<td>${​​​​​​​loanId}​​​​​​​</td>
		</tr>
		<tr>
			<td>Collateral Type:</td>
			<td>${​​​​​​​collateralType}​​​​​​​</td>
		</tr>
		<tr>
			<td>Owner Name:</td>
			<td>${​​​​​​​ownerName}​​​​​​​</td>
		</tr>
		<tr>
			<td>Owner Address:</td>
			<td>${​​​​​​​ownerAddress}​​​​​​​</td>
		</tr>
		<tr>
			<td>Owner Contact:</td>
			<td>${​​​​​​​ownerContact}​​​​​​​</td>
		</tr>
		<tr>
			<td>Current Value:</td>
			<td>${​​​​​​​currentValue}​​​​​​​</td>
		</tr>

		<tr>
			<td>Rate Per Sq Ft:</td>
			<td>${​​​​​​​ratePerSqFt}​​​​​​​</td>
		</tr>
		<tr>
			<td>Depreciation Rate:</td>
			<td>${​​​​​​​depreciationRate}​​​​​​​</td>
		</tr>
	

	</table>
	${body}
	<%@ include file="common/footer.jsp"%>
</body>
</html>
