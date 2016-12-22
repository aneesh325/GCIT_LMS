<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.services.AdminService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@include file="welcome.html" %>  
<%
	AdminService aservice = new AdminService();
	String publisherId = request.getParameter("publisherId");
	Publisher publisher = aservice.readPublisherByPK(Integer.parseInt(publisherId)); 
%>


<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Edit Publisher</h4>
</div>
<div class="modal-body">
	<form class="form-group" style="padding:5px;" action="EditPublisher" method="post">
		Enter Publisher Name: <input class="form-control" type="text" name="publisherName" value='<%=publisher.getPublisherName()%>'> 
		Enter Publisher Address: <input class="form-control" type="text" name="publisherAddress" value='<%=publisher.getPublisherAddress()%>'> 
		Enter Publisher Phone: <input class="form-control" type="text" name="publisherPhone"value='<%=publisher.getPublisherPhone()%>'>
		 <input type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>"/> 
		 <input type="submit" class="btn btn-default"/>
		 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</form>
</div>
