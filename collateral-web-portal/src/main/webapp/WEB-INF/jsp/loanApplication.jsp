<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apply For Loan</title>
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
    <%@ include file="common/UserHeader.jsp"%>
<%--     <font color="red">${errorMessage}</font> --%>
    <div class="container">
        <div class="list">

        </div>
 
        <h1><button type="button" class="btn btn-secondary btn-lg btn-block">Loan Application Form Details</button></h1>
        <div class="sub-container">
            <div class="error">${status}</div>
            <font color="red", size=12px>${errorMessage}</font>
            <spring:url value="/applyLoan" var="applicationUrl"></spring:url>
            <form:form method="POST" modelAttribute="loanApplication" action="${applicationUrl}">
                <table>
                    <tr>
                        <td><form:label path="customerId">Customer Id</form:label></td>
                        <td><form:input class="form-control" path="customerId" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="loanAmt">Loan Amount</form:label></td>
                        <td><form:input path="loanAmt" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="tenure">Tenure</form:label></td>
                        <td><form:input path="tenure" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="interestRate">Interest Rate</form:label></td>
                        <td><form:input path="interestRate" /></td>
                    </tr>
                    
                    <tr>
                        <td><form:label path="collateralType">Collateral Type</form:label></td>
                        <td>
                        	<form:select id="collateralType" path="collateralType">
                        		<option value="CASH_DEPOSIT">Cash Deposit</option>
                        		<option value="REAL_ESTATE">Real Estate</option>
                        	</form:select>
                         </td>
                    </tr>
                  
                  	<tr>
                        <td><form:label path="collateralValue">Current Value of Collateral</form:label></td>
                        <td><form:input path="collateralValue" /></td>
                    </tr>

                </table>
                <div class="btn-width">
                    <form:button class="btn btn-primary">Apply</form:button>
                </div>
 

            </form:form>
        </div>
    </div>
    <%@ include file="common/footer.jsp"%>
</body>
</html>