<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/portal/css/bootstrap.min.css">
<link rel="stylesheet" href="/portal/css/name.css/" type="text/css" />
<script src="/portal/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="common/header.jsp"%>

	<div class="container">
		<h1>
			<button type="button" class="btn btn-secondary btn-lg btn-block">Approve/Reject
				The Loan Status</button>
		</h1>
		<div class="sub-container">
			<%-- <div class="error">${loan}</div> --%>
			<div class="error">${status}</div>
			<font color="red">${errorMessage}</font>
		</div>


		<div class="sub-container">



			<table class="table table-bordered">
				<thead class="thead-light">
					<tr>
						<th scope="col">ApplicationId</th>
						<th scope="col">customerId</th>
						<th scope="col">Loan Amount</th>
						<th scope="col">Tenure</th>
						<th scope="col">Collateral Type</th>
						<th scope="col">Collateral Value</th>
						<th scope="col">Status</th>
						<th scope="col">Approve</th>
						<th scope="col">Reject</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${loans}" var="loa">
						<tr>
							<td>${loa.applicationId}</td>
							<td>${loa.customerId}</td>
							<td>${loa.loanAmt}</td>
							<td>${loa.tenure}</td>
							<td>${loa.collateralType}</td>
							<td>${loa.collateralValue}</td>
							<td>${loa.status}</td>
							<td><a type="button" class="btn btn-success"
								href="/portal/approveLoanApplication?applicationId=${loa.applicationId}">Approve</a></td>
							<td><a type="button" class="btn btn-warning"
								href="/portal/rejectLoanApplication?applicationId=${loa.applicationId}">Reject</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>





		<!--  	<table class="table table-striped">
				<caption>Here's your list of ${name}'s Todos :-</caption>
				<thead>
					<tr>
						<th>Description</th>
						<th>Target Date</th>
						<th>Is it Done?</th>




					</tr>
				</thead>
				<tbody>
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.desc}</td>
							<td><fmt:formatDate value="${todo.targetDate}"
									pattern="dd/MM/yyyy" /></td>
							<td>${todo.done}</td>
							<td><a type="button" class="btn btn-success"
								href="/update-todo?id=${todo.id}">Update</a></td>
							<td><a type="button" class="btn btn-warning"
								href="/delete-todo?id=${todo.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div> -->
	





	</div>
	</div>
	<%@ include file="common/footer.jsp"%>
</body>
</html>