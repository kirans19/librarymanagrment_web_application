<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
    <style type="text/css">

    body
    {
        background:#fff;
    }

    .payment
	{
		border:1px solid #f2f2f2;
		height:280px;
        border-radius:20px;
        background:#fff;
	}
   .payment_header
   {
	   background:purple;
	   padding:20px;
       border-radius:20px 20px 0px 0px;
	   
   }
   
   .check
   {
	   margin:0px auto;
	   width:50px;
	   height:50px;
	   border-radius:100%;
	   background:purple;
	   text-align:center;
   }
   
   .check i
   {
	   vertical-align:middle;
	   line-height:50px;
	   font-size:30px;
   }

    .content 
    {
        text-align:center;
    }

    .content  h1
    {
        font-size:25px;
        padding-top:25px;
    }

    .content a
    {
        width:200px;
        height:35px;
        color:#fff;
        border-radius:30px;
        padding:5px 10px;
        background:green;
        transition:all ease-in-out 0.3s;
    }

    .content a:hover
    {
        text-decoration:none;
        background:#000;
    }
   
</style>
    
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("username")==null)
	response.sendRedirect("login.jsp");
%><header>
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
	<c:if test="${usermode==3}"><br>
	
	<h3 class="text-left"><button class="btn btn-success" onclick="window.location.href='StaffMenu.jsp';">Back</button></h3>
<h3 class="text-center">Due Amount</h3>
<br>
	
	
	
<div class="container col-md-5" align="left">

		<div class="card">
			<div class="card-body">
		
<form action="ReaderDueSlip" method="post">
<fieldset class="form-group">
<label for="iSBN">Enter Reader ID:</label><br>
  <input type="text" id="lname" name="id" class="form-control" required="required"  pattern="^(0|[1-9][0-9]*)$"><br><br>
  <input type="submit"  class="btn btn-success" value="Submit">
  </fieldset>
</form>
</div>
</div>
</div>

<br>

<div class="row">

		<div class="container">
	
<form action="ReaderDueSlip">
<c:if test="${dueSlip.size()!=0}">	
<table class="table table-bordered">



<tbody>
<c:forEach var="dueSlip" items="${dueSlip}">
<tr>
	<td><b>Reader ID:</b></td>
	<td><c:out value="${dueSlip.id}" /></td>
	
</tr>
<tr>
	<td><b>Reader Name:</b></td>
	<td><c:out value="${dueSlip.first_name}" /> <c:out value="${dueSlip.last_name}" /></td>
	
</tr>
<tr>
	<td><b>Contact Number:</b></td>
	<td><c:out value="${dueSlip.mobile_no}" /></td>
	
</tr>
<tr>
	<td><b>Address:</b></td>
	<td><c:out value="${dueSlip.address}" /></td>
	
</tr>
<tr>
	<td><b>Due to Be Paid</b></td>
	<td><c:out value="${dueSlip.due_amount}" /></td>
	
</tr>
</c:forEach>
</tbody>

</table>
</c:if>	
</form>

<br>
<c:if test="${dueSlip.size()==0}">
<h3 style="text-align:center;"> No Transactions Performed By Reader !!</h3>
</c:if>
	</div>
	</div>
</c:if>
</body>
</html>