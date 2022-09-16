<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="ISO-8859-1">
<title>Library Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>

<body>
<%

response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("username")==null)
	response.sendRedirect("login.jsp");
%>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: purple">
			<div>
				<h4 style="color:#D1D9E1;">Library Management App&nbsp;&nbsp;</h4>
			</div>
			<form action="Logout">
				<input type="submit" class="btn btn-danger pull-right" value="Logout">
				</form>
		</nav>
	</header>
	<br>
	<button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/Profile';">Back</button>
	
	<br>
	<h3 align="center">Active Session History</h3>
<br>
<form action="SessionServlet">
<div class="scroll">
		<div class="container">
			<hr>
			<div class="container text-left">

<table class="table table-bordered">
<thead>
<tr>
					<th>Session ID</th>
					<th>Creation Time</th>
					<th>Browser Details</th>
					
					
</tr>
</thead>
<tbody>
<c:set var = "currSessionId" scope = "session" value ="${pageContext.session.id}" />
<c:forEach var="userSessionList" items="${userSessionList}">
<c:if test="${userSessionList.session_id==currSessionId}">
<tr style="background-color:#8FBC8F"> 



	<td><c:out value="${userSessionList.session_id}" /></td>
	<td><c:out value="${userSessionList.creation_time}" /></td>
	<td><c:out value="${userSessionList.browser_details}" /></td>
	
	
	<td>Current Session</td>
	
	
	


</tr>
</c:if>
</c:forEach>
<c:forEach var="userSessionList" items="${userSessionList}">
<c:if test="${userSessionList.session_id!=currSessionId}">
<tr style="background-color:#F2F2F2"> 



	<td><c:out value="${userSessionList.session_id}" /></td>
	<td><c:out value="${userSessionList.creation_time}" /></td>
	<td><c:out value="${userSessionList.browser_details}" /></td>
	
	
	
	
	
	<td>Previous Session</td>
	

</tr>

	
</c:if>
	
</c:forEach>

</tbody>
</table>


</div>
</div>
</div>
</form>
</body>
</html>