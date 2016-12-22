<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.services.AdminService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@include file="welcome.html" %>  
<%
	AdminService aservice = new AdminService();
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	Book book = aservice.readOneBook(bookId);
%>


<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Edit Book</h4>
</div>
<div class="modal-body">
	<form class="form-group" style="padding:5px;" action="EditBook" method="post">
		Enter New Title: <input class="form-control" type="text" name="title" value='<%=book.getTitle()%>'> 
		 <input type="hidden" name="bookId" value="<%=book.getBookId()%>"/> 
		 <button type="submit" class="btn btn-default">Update</button>
	</form>
</div>
