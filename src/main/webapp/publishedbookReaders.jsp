<%@ page import="java.sql.*"%>  
<%@ page import="login.dao.LoginDao"%>
<%@ page import="database.connection.DBConnection"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
<head>
<title>Libray Management Application</title>
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
        background:#f2f2f2;
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
<c:if test="${usermode==2}">


<% 
String publisherId=LoginDao.id;
System.out.println(publisherId);
try{  
Connection con=DBConnection.getConnection();
PreparedStatement ps=con.prepareStatement("SELECT book_borrowlog.id,user_data.first_name,user_data.last_name FROM book_borrowlog RIGHT JOIN user_data on book_borrowlog.id=user_data.id RIGHT JOIN book_catalogue on book_borrowlog.ISBN=book_catalogue.ISBN WHERE book_catalogue.publisherId=? group by id;");
ps.setString(1,publisherId);
ResultSet resultset=ps.executeQuery();
%>
<% if(resultset.next()){ %>
<br><button class="btn btn-success" onclick="window.location.href='PublisherMenu.jsp';">Back</button>
<div class="row">

		<div class="container">
			<br>
			<h3 class="text-center">My Book Readers</h3>
			<hr>
			<br>
			<table class="table table-bordered"> 
<thead>
     <tr>
		<th>Reader Id</th>
		<th>Reader Name</th>
	</tr>
</thead>
<tbody>

<% while(resultset.next()){ %>

 <TR>
     <TD> <%= resultset.getString(1) %></TD>
     <TD> <%= resultset.getString(2)+" " %><%= resultset.getString(3) %></TD>
 </TR> 
  
<% } %>
</tbody>	
</table>
</div>
</div>
<%} else { %>

<div class="container">
<div class="row">
 <div class="col-md-6 mx-auto mt-5">
 <div class="payment">
 <div class="payment_header">
 <div class="check"><i class="fa fa-check" aria-hidden="true"></i></div>
 </div>
 <div class="content">
<h1>No Readers Purchased!!</h1><br><br>
<a href="/LibraryManagementSystemWebApplication/PublisherMenu.jsp">Go to Menu</a><br>
 </div>
         
</div>
</div>
</div>
</div>





<% } con.close();
}
catch(SQLException e){
	System.out.println("Error");
	e.printStackTrace();
}
%>
</c:if>
 </body>
 </html>