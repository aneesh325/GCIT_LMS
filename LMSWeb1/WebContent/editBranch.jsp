<%@page import="com.gcit.lms.services.Librarian"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>

<%@page import="com.gcit.lms.entity.Author"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="welcome.html" %>  
    <%
     Librarian lservice = new Librarian();
     Integer branchId = Integer.parseInt(request.getParameter("branchId"));
     LibraryBranch lib = lservice.readBranchByPK(branchId);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>LMS</title>
</head>
<body>
	<h1>GCIT Library Management System</h1>
	<h2>Edit the library Branch</h2>
	
	<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Edit Publisher</h4>
</div>
<div class="modal-body">
	<form class="form-group" style="padding:5px;" action="editBranch" method="post">
		Enter branch Name: <input class="form-control" type="text" name="branchName" value='<%=lib.getBranchName()%>'> 
		Enter branch  Address: <input class="form-control" type="text" name="branchAddress" value='<%=lib.getBranchAddress()%>'> 
		 <input type="hidden" name="branchId" value='<%=lib.getBranchId()%>'/> 
		 <input type="submit" class="btn btn-default"/>
		 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</form>
</div>

