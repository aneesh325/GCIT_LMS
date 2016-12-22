<%@page import="com.gcit.lms.services.AdminService"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="welcome.html" %>    
<%
AdminService aservice = new AdminService(); 
List<Borrower> bList = aservice.readAllBorrower(); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BorowerList</title>
</head>
<body>
<h1>GCIT Library Management</h1>


<div class="col-md-6">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>cardNo</th>
                <th>Borrower Name</th>
                <th>Borrower Address</th>
                <th>Borrower Phone</th>
              </tr>
            </thead>
            <tbody>
            <%
            for(Borrower b : bList){%>
<tr> 
 <td><%=b.getCardNo()%></td>	
  <td><%=b.getName()%></td>
   <td><%=b.getAddress()%></td>
   <td><%=b.getPhone()%></td>
   <td><button type="button" class="btn btn-warning"onClick="javascript:location.href='EditBorrower.jsp?cardNo=<%=b.getCardNo()%>'">Update</button></td>
 
 
   <td><button type="button" class="btn btn-danger"onClick="javascript:location.href='DelBorrower?cardNo=<%=b.getCardNo()%>'">Delete</button></td>
 
 
  </tr>
<%} %>
   <td><button type="button" class="btn btn-success"onClick="javascript:location.href='AddBorrower.jsp'">Add</button></td>

            </tbody>
          </table>
        <button type="button" class="btn btn-success"
				onClick="location.href='Index.jsp';">Cancel</button>
			<br />  
        </div>
      </div>
</body>
</html>