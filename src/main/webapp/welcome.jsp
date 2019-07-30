<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create an account</title>
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>


	<div class="container">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>Welcome ${currentUser.username}</h2>

			<c:if test="${not empty currentUser.savingAccount}">
    			Your Saving Account is ${currentUser.savingAccount}
		</c:if>
			<c:choose>
				<c:when test="${empty currentUser.savingAccount}">
					<form:form method="POST" action="${contextPath}/opensavingaccount" modelAttribute="currentUser">
						<input type="submit" value="Open Saving Account" />
					</form:form>
				</c:when>
				<c:otherwise>
    		</c:otherwise>
			</c:choose>








			<h2>
				<a onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>

		</c:if>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
