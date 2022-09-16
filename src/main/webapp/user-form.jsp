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
	<br><button class="btn btn-success" onclick="window.location.href='/LibraryManagementSystemWebApplication/list';">Back</button>
	
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${book != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${book == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${book != null}">
            			Edit Book Details
            		</c:if>
						<c:if test="${book == null}">
            			Add New Book
            		</c:if>
					</h2>
				</caption>
				
            	<c:if test="${book != null}">
            			<fieldset class="form-group">
						<input type="hidden"
						value="<c:out value='${book.ISBN}' />" class="form-control"
						name="ISBN" required="required"  pattern="^(0|[1-9][0-9]*)$">
				</fieldset>
            	</c:if>	
				
				<c:if test="${book == null}">
            			<fieldset class="form-group">
						<label>ISBN</label> <input type="number"
						value="<c:out value='${book.ISBN}' />" class="form-control"
						name="ISBN" required="required"  pattern="^(0|[1-9][0-9]*)$">
				</fieldset>
            	</c:if>	

				
			
				
				<fieldset class="form-group">
					<label>Book Title</label> <input type="text"
						value="<c:out value='${book.title}' />" class="form-control"
						name="title" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Book Price</label> <input type="number"
						value="<c:out value='${book.price}' />" class="form-control"
						name="price" >
				</fieldset>

				<fieldset class="form-group">
					<label>Edition</label> <input type="text"
						value="<c:out value='${book.edition}' />" class="form-control"
						name="edition" pattern="^[A-Za-z]+">
				</fieldset>
				
				<br>
				<fieldset class="form-group">
					<label>Category &nbsp&nbsp&nbsp&nbsp</label>
					<select  name="category">
				   <option value="0">Novel</option>
				   <option value="1">Story</option>
				   <option value="2">General</option>
				   </select>
  
				</fieldset>
				
	
    
				
				<!-- ----------<fieldset class="form-group">
					<label>Publisher Id</label> <input type="number"
						value="<c:out value='${book.publisherId}' />" class="form-control"
						name="publisherId">
				</fieldset>-------- -->
				<br>
				
				<fieldset class="form-group">
					<label>PublisherId&nbsp&nbsp&nbsp&nbsp</label>
					<select  name="publisherId">
					<option value="<c:out value='${book.publisherId}' />">${book.publisherId}</option>
				   <c:forEach var="publisherList" items="${publisherList}">
				  <c:if test="${publisherList.id != book.publisherId }">
				   <option value="${publisherList.id}">${publisherList.id}</option>
				   </c:if>
				   </c:forEach>
				   </select>
  
				</fieldset>
				<br>
				
				
				
				
				
				<fieldset class="form-group">
					<label>Author No</label> <input type="text"
						value="<c:out value='${book.authorNo}' />" class="form-control"
						name="authorNo" pattern="^[A-Za-z0-9]+">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Book Description</label> <input type="text"
						value="<c:out value='${book.bookInfo}' />" class="form-control"
						name="bookInfo">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Published Year</label> <input type="number"
						value="<c:out value='${book.publishedYear}' />" class="form-control"
						name="publishedYear"  size="4" >
				</fieldset>
				
				<fieldset class="form-group">
					<label>Book Count</label> <input type="number"
						value="<c:out value='${book.count}' />" class="form-control"
						name="count">
				</fieldset>
				

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
	</c:if>
</body>
</html>