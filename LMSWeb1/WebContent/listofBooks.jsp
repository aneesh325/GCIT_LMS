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
Librarian libservice = new Librarian(); 
List<Book> book = libservice.getBooks(branchId);
List<BookCopies> cop = libservice.getCopies(branchId);
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>The list of books in the library are</h1>
	<table>
		<tr>
			<th>#</th>
			<th>BookList</th>
			<th>Copies</th>
		</tr>
		<%
			for (Book b : book) {
		%>
		<tr>
			<td><%=book.indexOf(b) + 1%></td>
			<td><%=b.getTitle()%></td>
			<td><%=cop.get(book.indexOf(b)).getNoOfCopies()%></td>
			<form action="listofBooks" method="post">
				<input type="hidden" name="bookId" value='<%=b.getBookId()%>'>
				<input type="hidden" name="branchId" value='<%=branchId%>'>
				
				<td> Enter new copies: <input type="text"
						name="noOfCopies"
						value='<%=cop.get(book.indexOf(b)).getNoOfCopies()%>'></span>
		 <input type="submit" class="btn btn-default"/>
		
		</tr>
		</form>
	</table>
	<%} %>

</body>
</html>