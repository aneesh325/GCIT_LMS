<%@page import="com.gcit.lms.services.BorrowerService"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="com.gcit.lms.services.Librarian"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@include file="welcome.html" %>    
<%
int branchId = Integer.parseInt(request.getParameter("branchId"));
int cardNo = Integer.parseInt(request.getParameter("cardNo")); 
BorrowerService borrservice = new BorrowerService(); 
List<Book> book = borrservice.getBooks(branchId);
Librarian libservice = new Librarian(); 
List<BookCopies> cop = libservice.getCopies(branchId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div class="col-md-6">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>BookList</th>
              </tr>
            </thead>
            <tbody>
            <%for(Book b : book){%>
<tr> 
 <td><%=book.indexOf(b) + 1%></td>	
  <td><%=b.getTitle()%></td>
  <form action="Borr2" method="post">
				<input type="hidden" name="bookId" value='<%=b.getBookId()%>'>
				<input type="hidden" name="branchId" value='<%=branchId%>'>
				<input type="hidden" name="cardNo" value='<%=cardNo%>'>   
				<td><button type="submit" class="btn btn-success"onClick="location.href='Index.jsp';">Checkout</button></td>
  </tr>				
  
<%} %></form>
            </tbody>
          </table>
        </div>
      </div>
</body>
</html>
