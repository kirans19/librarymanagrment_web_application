<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Library Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<script type="text/javascript">
        function preventBack() {
            window.history.forward(); 
        }
          
        setTimeout("preventBack()", 0);
          
        window.onunload = function () { null };
    </script>
</head>
<body>
<%
//response.setHeader("Content-Security-Policy","script-src http://localhost:8080/LibraryManagementSystemWebApplication/login");
//response.setHeader("Content-Security-Policy","script-src http://localhost:8080/LibraryManagementSystemWebApplication/list");

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
	<c:if test="${usermode==3}">
	<br><button class="btn btn-success" onclick="window.location.href='StaffMenu.jsp';">Back</button>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Book Catalogue</h3>
			<hr>
			
			  <p style="text-align:center">${message}</p>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Books</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
				<tr>
					<th>ISBN</th>
						<th>Book Title</th>
						<th>Book Price</th>
						<th>Edition</th>
						<th>Category</th>
						<th>Publisher Id</th>
						<th>Author Number</th>
						<th>Book Description</th>
						<th>Published Year</th>
						<th>Availability</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="books" items="${listBooks}">
						<c:if test="${books.count>-1}">
						<tr>
							<td><c:out value="${books.ISBN}" /></td>
							<td><c:out value="${books.title}" /></td>
							<td><c:out value="${books.price}" /></td>
							<td><c:out value="${books.edition}" /></td>
							<c:if test="${books.category == 0}">
							<td><c:out value="Novel" /></td>
							</c:if>
							<c:if test="${books.category == 1}">
							<td><c:out value="Story" /></td>
							</c:if>
							<c:if test="${books.category == 2}">
							<td><c:out value="General" /></td>
							</c:if>
							<td><c:out value="${books.publisherId}" /></td>
							<td><c:out value="${books.authorNo}" /></td>
							<td><c:out value="${books.bookInfo}" /></td>
							<td><c:out value="${books.publishedYear}" /></td>
							<c:if test="${books.count==-1}">
							<td>Deleted</td>
							</c:if>
							<c:if test="${books.count>-1}">
							<td><c:out value="${books.count}" /></td>
							</c:if>
							
							<td><a href="edit?ISBN=<c:out value='${books.ISBN}' />">Update</a>
							<br>
							<c:if test="${books.count>=0}">
								<a href="delete?ISBN=<c:out value='${books.ISBN}' />">Delete</a>
							</c:if>
							 </td>
						</tr>
						</c:if>
					</c:forEach>
					<br>
		
				</tbody>

			</table>
		</div>
	</div>
	</c:if>
</body>
</html>
