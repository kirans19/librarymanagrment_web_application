<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
     <style>
    .btn-primary{
   	    margin-left: 10px;
   	}
   </style>
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
			<br> <button class="btn btn-primary" onclick="window.location.href='/LibraryManagementSystemWebApplication/Profile';">Profile</button>
			<form action="Logout">
				<input type="submit" class="btn btn-danger pull-right" value="Logout">
				</form>
		
		</nav>
</header>
<c:if test="${usermode==2}">


<div class="row">

		<div class="container">
			<br>
			<!-- ----<h3 class="text-left"><button class="btn btn-success" onclick="history.back()">Back</button></h3> -->
			<h3 class="text-center">Publisher Menu </h3>
			
			<hr>
			<br>
	<table class="table table-bordered"> 

	<tbody>
		<tr>
		<td>Reader Mode</td>
		<td style="text-align:center">	
		<button class="btn btn-success" onclick="window.location.href='ReaderMenu.jsp';">CLICK</button>
		</td>
		</tr>
 	
 	<tr>
	<td>Published Book List</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='publishedbooklist.jsp';">CLICK</button>
 	</td>
 	</tr>
 	
 	<tr>
	<td>My Books Reader List</td>
	<td style="text-align:center">	
 	<button class="btn btn-success" onclick="window.location.href='publishedbookReaders.jsp';">CLICK</button>
 	</td>
 	</tr>
	
	
</tbody>
</table>

</div>

</div>
</c:if>
</body>

</html>