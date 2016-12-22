<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.services.AdminService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@include file="welcome.html" %>  
<%
	AdminService aservice = new AdminService();
	String publisherId = request.getParameter("publisherId");
	String authorId = request.getParameter("authorId");
Author author = aservice.readOneAuthor(Integer.parseInt(authorId)); // readAuthorByPK(author)
%>


<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Edit Publisher</h4>
</div>
<div class="modal-body">
	<form class="form-group" style="padding:5px;" action="EditAuthor" method="post">
		Enter Author Name: <input class="form-control" type="text" name="authorName" value='<%=author.getAuthorName()%>'> 
		 <input type="hidden" name="authorId" value="<%=author.getAuthorId()%>"/> 
		 <input type="submit" class="btn btn-default"/>
		 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</form>
</div>
