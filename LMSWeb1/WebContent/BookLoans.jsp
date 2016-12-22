<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.services.AdminService"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.services.BorrowerService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="welcome.html" %>    
<%
AdminService aservice = new AdminService(); 
List<BookLoans> blList = aservice.ReadAllBookLoans(); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookLoansList</title>
</head>
<body>
<h1>GCIT Library Management</h1>


<div class="col-md-6">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>bookId</th>
                <th>Library branchId</th>
                <th>Card Number</th>
                <th>dueDate</th>
              </tr>
            </thead>
            <tbody>
            <%
            for(BookLoans b : blList){%>
<tr> 
 <td><%=b.getBooks().getBookId()%></td>	
  <td><%=b.getBranch().getBranchId()%></td>
 <td><%=b.getCards().getCardNo()%></td>
 <td><%=b.getDateOut().toString()%></td>
 <form action="BookLoans" method="post">
 <td> Enter new DueDate: <input type="text"name="newDueDate"></span>
 		 <input type="hidden" name="bookId" value="<%=b.getBooks().getBookId()%>"/> 
		  <input type="hidden" name="branchId" value="<%=b.getBranch().getBranchId()%>"/>
		  <input type="hidden" name="cardNo" value="<%=b.getCards().getCardNo()%>"/>
		  		 <button type="submit" class="btn btn-default">Update</button>
  </tr>
  </form>
<%} %>
 <button type="button" class="btn btn-success"
				onClick="location.href='Index.jsp';">Cancel</button>
			<br />  
            </tbody>
          </table>
        </div>
      </div>
</body>
</html>