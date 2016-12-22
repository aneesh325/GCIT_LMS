<%@page import="com.gcit.lms.services.AdminService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@include file="welcome.html" %>    
<%
AdminService aservice = new AdminService(); 
List<Book> bList = aservice.readAllBooks();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookList</title>
</head>
<body>
<h1>GCIT Library Management</h1>


<div class="col-md-6">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>title</th>
                <th>publisherId</th>
              </tr>
            </thead>
            <tbody>
            <%
            for(Book b : bList){%>
<tr> 
 <td><%=bList.indexOf(b)+1%></td>	
  <td><%=b.getTitle()%></td>
   <td><button type="button" class="btn btn-warning"onClick="javascript:location.href='EditBook.jsp?bookId=<%=b.getBookId()%>'">Update</button></td>
 
   <td><button type="button" class="btn btn-danger"onClick="javascript:location.href='DelBook?bookId=<%=b.getBookId()%>'">Delete</button></td>
 
  </tr>
<%} %>
   <td><button type="button" class="btn btn-success"onClick="javascript:location.href='AddBook.jsp'">Add</button>    <button type="button" class="btn btn-success"
				onClick="location.href='Index.jsp';">Cancel</button>
			<br />  </td>


            </tbody>
          </table>
                 
          
        </div>
      </div>
</body>
</html>