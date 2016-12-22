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
List<Publisher> pubList = aservice.readAllPublishers();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibraryList</title>
</head>
<body>
<h1>GCIT Library Management</h1>


<div class="col-md-6">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>PublisherName</th>
                <th>PublisherAddress</th>
              </tr>
            </thead>
            <tbody>
            <%
            for(Publisher p : pubList){%>
<tr> 
 <td><%=pubList.indexOf(p)+1%></td>	
  <td><%=p.getPublisherName()%></td>
 <td><%=p.getPublisherAddress() %></td>
   <td><button type="button" class="btn btn-warning"onClick="javascript:location.href='EditPublisher.jsp?publisherId=<%=p.getPublisherId()%>'">Update</button></td>
 
 
  <td><button type="button" class="btn btn-danger"onClick="javascript:location.href='Delpublisher?publisherId=<%=p.getPublisherId()%>'">Delete</button></td>
 
 
  </tr>
<%} %>
   <td><button type="button" class="btn btn-success"onClick="javascript:location.href='Addpublisher.jsp'">Add</button>
    <button type="button" class="btn btn-success"
				onClick="location.href='Index.jsp';">Cancel</button>
			<br />  </td>

            </tbody>
          </table>
        </div>
      </div>
</body>
</html>