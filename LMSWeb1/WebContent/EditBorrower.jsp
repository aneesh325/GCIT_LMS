<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.services.AdminService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@include file="welcome.html" %>  
<%
	AdminService aservice = new AdminService();
	Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
	Borrower borr = aservice.readBorrowerByPK(cardNo); 
%>


<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Edit Borrower</h4>
</div>
<div class="modal-body">
	<form class="form-group" style="padding:5px;" action="EditBorrower" method="post">
		Enter Borrower Name: <input class="form-control" type="text" name="name" value='<%=borr.getName()%>'> 
		Enter Borrower Address: <input class="form-control" type="text" name="address" value='<%=borr.getAddress()%>'> 
		Enter Borrower Phone: <input class="form-control" type="text" name="phone"value='<%=borr.getPhone()%>'>
		 <input type="hidden" name="cardNo" value="<%=borr.getCardNo()%>"/> 
		 <input type="submit" class="btn btn-default"/>
		 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</form>
</div>
