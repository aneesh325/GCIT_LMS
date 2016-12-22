<%@page import="com.gcit.lms.services.BorrowerService"%>
<%@page import="com.gcit.lms.services.AdminService"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="welcome.html" %>    
<%
BorrowerService bservice = new BorrowerService(); 
List<LibraryBranch> lib = bservice.listOfBranches();
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
                <th>LibraryBranch</th>
                <th>LibraryAddress</th>
              </tr>
            </thead>
            <tbody>
            <%
            for(LibraryBranch l : lib){%>
<tr> 
 <td><%=lib.indexOf(l)+1%></td>	
  <td><%=l.getBranchName()%></td>
  <td><%=l.getBranchAddress()%></td>
   <td><button type="button" class="btn btn-warning"onClick="javascript:location.href='editBranch.jsp?branchId=<%=l.getBranchId()%>'">Update</button></td>
 
 
   <td><button type="button" class="btn btn-danger"onClick="javascript:location.href='listofBooks.jsp?branchId=<%=l.getBranchId()%>'">ListofBooks</button></td>
 
 
  </tr>
<%} %>
   <td><button type="button" class="btn btn-success"onClick="javascript:location.href='Index.jsp'">Cancel</button></td>

            </tbody>
          </table>
          
        </div>
      </div>
</body>
</html>