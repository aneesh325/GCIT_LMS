<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.services.BorrowerService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
BorrowerService bservice = new BorrowerService(); 
List<Borrower> borrList = bservice.listOfBorr();
%>
  <%Borrower borr = (Borrower) request.getAttribute("card"); %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Borrower</title>
</head>
<body>


<h2 align="center">Borrower Menu</h2>
<hr />
	Enter Card No: <input type="text" name="cardNo"/>
</div>
			<form action="borrower" method="post">
			</span><br /> <span><input type="hidden" name="cardNo">
			
			
	</form>
  	</tr> 

	<button type="button" class="btn btn-sm btn-default" style="margin:5px; width:125px;" onClick="location.href='BorrOptions.jsp';">Go
	<button type="button" class="btn btn-sm btn-default" style="margin:5px; width:125px;" onClick="location.href='Index.jsp';">Cancel

</body>
</html>