<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/portal/css/bootstrap.min.css">
<link rel="stylesheet" href="/portal/css/name.css/" type="text/css" />
<script src="/portal/js/bootstrap.min.js"></script>
<!-- <style>
.list{
margin-left: 82%;
}
.btn {
	margin-left: auto;
	margin-right: auto;
	width: 100%;
}
</style> -->
</head>
<body>
<body>
	<%@ include file="common/header.jsp"%>
<%-- 	<font color="red">${errorMessage}</font> --%>
	<div class="container">
		<div class="list">
			
		</div>

		<h1><button type="button" class="btn btn-secondary btn-lg btn-block">Enter the Cash Form Details</button></h1>
		<div class="sub-container">
			<div class="error">${status}</div>
			<font color="red", size=12px>${errorMessage}</font>
			<form:form method="POST" modelAttribute="cash" action="cashdeposit">
				<table>
					<tr>
						<td><form:label path="collateralId">Collateral Id</form:label></td>
						<td><form:input class="form-control" path="collateralId" /></td>
					</tr>
					<tr>
						<td><form:label path="loanId">Loan Id</form:label></td>
						<td><form:input path="loanId" /></td>
					</tr>
					<tr>
						<td><form:label path="collateralType">Collateral Type</form:label></td>
						<td><form:input path="collateralType" /></td>
					</tr>
					<tr>
						<td><form:label path="ownerName">Owner's Name</form:label></td>
						<td><form:input path="ownerName" /></td>
					</tr>
					<tr>
						<td><form:label path="ownerAddress">Owner's Address</form:label></td>
						<td><form:input path="ownerAddress" /></td>
					</tr>
					<tr>
						<td><form:label path="ownerContact">Owner's Contact</form:label></td>
						<td><form:input path="ownerContact" /></td>
					</tr>
					<tr>
						<td><form:label path="currentValue">Current Value</form:label></td>
						<td><form:input path="currentValue" /></td>
					</tr>
					<tr>
						<td><form:label path="interestRate">Interest Rate</form:label></td>
						<td><form:input path="interestRate" /></td>
					</tr>
					<tr>
						<td><form:label path="depositAmount">Deposit Amount</form:label></td>
						<td><form:input path="depositAmount" /></td>
					</tr>
					<tr>
						<td><form:label path="lockPeriod">Lock Period</form:label></td>
						<td><form:input path="lockPeriod" /></td>
					</tr>
				</table>
				<div class="btn-width">
					<form:button class="btn btn-primary">Save Cash</form:button>
				</div>
				


			</form:form>
		</div>
	</div>
	<%@ include file="common/footer.jsp"%>
</body>
</html>