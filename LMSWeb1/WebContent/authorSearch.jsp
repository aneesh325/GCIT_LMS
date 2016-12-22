<%@page import="com.gcit.lms.services.AdminService"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@include file="welcome.html" %>    
<%
AdminService aservice = new AdminService(); 
int count = aservice.getAuthorsCount();
List<Author> aList1= new ArrayList<Author>();
if(request.getAttribute("aList1")!= null)
{
aList1 = (List<Author>)request.getAttribute("aList1");	
}
else
{
aList1 = aservice.readAllAuthorsbySearch(null); 	
}	
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AuthorList</title>
</head>
<body>
<h1>GCIT Library Management</h1>
<form action ="searchAuthors" method = "get"> 
<div class ="input-group">
	<input type = "text" class = "form-control" placeholder = "AuthorName"
			aria-describedby="basic-addon1" name = "searchString">
	<button type="submit" class = "btn-btn-success">Go</button>		
</div>
</form>

<div class="col-md-6">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>AuthorName</th>
              </tr>
            </thead>
            <tbody>
            <%
            for(Author a : aList1){%>
<tr>



 
 <td><%=aList1.indexOf(a)+1%></td>	
  <td><%=a.getAuthorName()%></td>
   <td><button type="button" class="btn btn-warning"onClick="javascript:location.href='EditAuthor.jsp?authorId=<%=a.getAuthorId()%>'">Update</button></td>
 
 
   <td><button type="button" class="btn btn-danger"onClick="javascript:location.href='DelAuthor?authorId=<%=a.getAuthorId()%>'">Delete</button></td>
 
 
  </tr>
<%} %>
   <td><button type="button" class="btn btn-success"onClick="javascript:location.href='AddAuthor.jsp'">Add</button>
    <button type="button" class="btn btn-success"
				onClick="location.href='Index.jsp';">Cancel</button>
			<br />  </td>

            </tbody>
          </table>
        </div>
      </div>
</body>
</html>